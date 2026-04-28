package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.CreateListingRequest;
import com.vargas.horsemarket.application.dto.ListingResponse;
import com.vargas.horsemarket.application.service.ListingResponseMapper;
import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.domain.valueobject.Money;
import com.vargas.horsemarket.shared.exception.ForbiddenActionException;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Caso de uso: Crear un nuevo anuncio de caballo.
 * Solo usuarios con rol SELLER pueden crear anuncios.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateListingUseCase {

    private final HorseListingRepository listingRepository;
    private final UserRepository userRepository;
    private final ListingResponseMapper responseMapper;

    @Transactional
    public ListingResponse execute(CreateListingRequest request) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User seller = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!seller.canCreateListing()) {
            throw new ForbiddenActionException("Solo usuarios SELLER activos pueden crear anuncios");
        }

        log.info("Creando anuncio para vendedor ID: {}", seller.getId());

        HorseListing listing = HorseListing.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .horseName(request.getHorseName())
                .breed(request.getBreed())
                .ageYears(request.getAgeYears())
                .sex(request.getSex())
                .discipline(request.getDiscipline())
                .level(request.getLevel())
                .price(Money.of(request.getPrice(), request.getCurrencyCode()))
                .location(request.getLocation())
                .status(ListingStatus.DRAFT)
                .premium(false)
                .sellerId(seller.getId())
                .createdAt(LocalDateTime.now())
                .build();

        HorseListing saved = listingRepository.save(listing);
        log.info("Anuncio creado con ID: {}", saved.getId());

        return responseMapper.toResponse(saved, seller);
    }
}
