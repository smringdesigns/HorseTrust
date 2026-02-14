package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.CreateRatingRequest;
import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.Rating;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.RatingRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.shared.exception.BusinessRuleException;
import com.vargas.horsemarket.shared.exception.DuplicateResourceException;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Caso de uso: Calificar a un vendedor post-venta.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RatingUseCase {

    private final RatingRepository ratingRepository;
    private final HorseListingRepository listingRepository;
    private final UserRepository userRepository;

    @Transactional
    public void rateseller(CreateRatingRequest request) {
        String buyerEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User buyer = userRepository.findByEmail(buyerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Comprador no encontrado"));

        HorseListing listing = listingRepository.findById(request.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", request.getListingId()));

        if (listing.getStatus() != ListingStatus.SOLD) {
            throw new BusinessRuleException("Solo se puede calificar después de concluida la venta");
        }

        if (buyer.getId().equals(listing.getSellerId())) {
            throw new BusinessRuleException("No puedes calificarte a ti mismo");
        }

        if (ratingRepository.existsByListingIdAndBuyerId(listing.getId(), buyer.getId())) {
            throw new DuplicateResourceException("Ya calificaste a este vendedor por este anuncio");
        }

        Rating rating = Rating.builder()
                .listingId(listing.getId())
                .buyerId(buyer.getId())
                .sellerId(listing.getSellerId())
                .score(request.getScore())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        rating.validateBeforeSave();
        ratingRepository.save(rating);

        // Actualizar el promedio del vendedor
        Double newAverage = ratingRepository.calculateAverageRatingBySellerId(listing.getSellerId());
        userRepository.findById(listing.getSellerId()).ifPresent(seller -> {
            seller.setAverageRating(newAverage);
            userRepository.save(seller);
        });

        log.info("Calificación creada: comprador {} -> vendedor {} = {}", buyer.getId(), listing.getSellerId(), request.getScore());
    }
}
