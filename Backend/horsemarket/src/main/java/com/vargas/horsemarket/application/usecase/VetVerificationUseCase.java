package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.CreateVetRecordRequest;
import com.vargas.horsemarket.application.dto.VetRecordResponse;
import com.vargas.horsemarket.application.dto.VetVerificationRequest;
import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.enums.VerificationStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.model.VeterinaryRecord;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.domain.repository.VeterinaryRecordRepository;
import com.vargas.horsemarket.shared.exception.BusinessRuleException;
import com.vargas.horsemarket.shared.exception.ForbiddenActionException;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Caso de uso CORE: Verificación veterinaria de anuncios.
 * Solo usuarios VET verificados pueden aprobar o rechazar.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VetVerificationUseCase {

    private final VeterinaryRecordRepository vetRecordRepository;
    private final HorseListingRepository listingRepository;
    private final UserRepository userRepository;

    /**
     * El veterinario crea el registro y comienza la revisión.
     */
    @Transactional
    public VetRecordResponse createRecord(CreateVetRecordRequest request) {
        User vet = getCurrentVet();
        log.info("Veterinario {} iniciando revisión del anuncio {}", vet.getId(), request.getListingId());

        HorseListing listing = listingRepository.findById(request.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", request.getListingId()));

        if (listing.getStatus() != ListingStatus.PUBLISHED &&
                listing.getStatus() != ListingStatus.UNDER_VERIFICATION) {
            throw new BusinessRuleException("El anuncio debe estar PUBLISHED o UNDER_VERIFICATION para ser revisado");
        }

        if (vetRecordRepository.findByListingId(request.getListingId()).isPresent()) {
            throw new BusinessRuleException("El anuncio ya tiene un registro veterinario activo");
        }

        VeterinaryRecord record = VeterinaryRecord.builder()
                .listingId(request.getListingId())
                .vetId(vet.getId())
                .status(VerificationStatus.IN_PROGRESS)
                .generalObservations(request.getGeneralObservations())
                .diagnoses(request.getDiagnoses())
                .vaccines(request.getVaccines())
                .lastVaccinationDate(request.getLastVaccinationDate())
                .lastCheckupDate(request.getLastCheckupDate())
                .healthCertificateUrl(request.getHealthCertificateUrl())
                .createdAt(LocalDateTime.now())
                .build();

        // Actualizar estado del anuncio
        listing.sendToVerification();
        listingRepository.save(listing);

        VeterinaryRecord saved = vetRecordRepository.save(record);
        log.info("Registro veterinario creado con ID: {}", saved.getId());

        return toResponse(saved, vet);
    }

    /**
     * El veterinario aprueba o rechaza el anuncio.
     */
    @Transactional
    public VetRecordResponse verify(Long recordId, VetVerificationRequest request) {
        User vet = getCurrentVet();

        VeterinaryRecord record = vetRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("VeterinaryRecord", recordId));

        if (!record.getVetId().equals(vet.getId())) {
            throw new ForbiddenActionException("Solo el veterinario asignado puede realizar la verificación");
        }

        HorseListing listing = listingRepository.findById(record.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", record.getListingId()));

        if (Boolean.TRUE.equals(request.getApproved())) {
            record.approve();
            listing.markAsVerified();
            log.info("Anuncio {} APROBADO por veterinario {}", listing.getId(), vet.getId());
        } else {
            String reason = request.getRejectionReason() != null
                    ? request.getRejectionReason()
                    : "Sin razón especificada";
            record.reject(reason);
            listing.markAsRejected();
            log.info("Anuncio {} RECHAZADO por veterinario {}. Razón: {}", listing.getId(), vet.getId(), reason);
        }

        listingRepository.save(listing);
        VeterinaryRecord updated = vetRecordRepository.save(record);

        return toResponse(updated, vet);
    }

    private User getCurrentVet() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User vet = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!vet.canVerifyListing()) {
            throw new ForbiddenActionException("Solo veterinarios verificados pueden realizar verificaciones");
        }
        return vet;
    }

    private VetRecordResponse toResponse(VeterinaryRecord record, User vet) {
        return VetRecordResponse.builder()
                .id(record.getId())
                .listingId(record.getListingId())
                .vetId(record.getVetId())
                .vetName(vet.getFullName())
                .status(record.getStatus())
                .generalObservations(record.getGeneralObservations())
                .diagnoses(record.getDiagnoses())
                .vaccines(record.getVaccines())
                .lastVaccinationDate(record.getLastVaccinationDate())
                .lastCheckupDate(record.getLastCheckupDate())
                .healthCertificateUrl(record.getHealthCertificateUrl())
                .rejectionReason(record.getRejectionReason())
                .approvedAt(record.getApprovedAt())
                .rejectedAt(record.getRejectedAt())
                .createdAt(record.getCreatedAt())
                .build();
    }
}
