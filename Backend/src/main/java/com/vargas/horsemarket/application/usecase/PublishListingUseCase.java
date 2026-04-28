package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.ListingResponse;
import com.vargas.horsemarket.application.service.ListingResponseMapper;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.shared.exception.ForbiddenActionException;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Caso de uso: Publicar un anuncio (pasar de DRAFT a PUBLISHED).
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublishListingUseCase {

    private final HorseListingRepository listingRepository;
    private final UserRepository userRepository;
    private final ListingResponseMapper responseMapper;

    @Transactional
    public ListingResponse execute(Long listingId) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User seller = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        HorseListing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", listingId));

        if (!listing.getSellerId().equals(seller.getId())) {
            throw new ForbiddenActionException("Solo el vendedor puede publicar su propio anuncio");
        }

        log.info("Publicando anuncio ID: {} para vendedor: {}", listingId, seller.getId());
        listing.publish(); // regla de dominio

        HorseListing published = listingRepository.save(listing);
        return responseMapper.toResponse(published, seller);
    }
}
