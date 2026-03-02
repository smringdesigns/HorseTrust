package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.model.Rating;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de dominio para persistencia de calificaciones.
 */
public interface RatingRepository {

    Rating save(Rating rating);

    Optional<Rating> findById(Long id);

    List<Rating> findBySellerId(Long sellerId);

    Optional<Rating> findByListingIdAndBuyerId(Long listingId, Long buyerId);

    Double calculateAverageRatingBySellerId(Long sellerId);

    boolean existsByListingIdAndBuyerId(Long listingId, Long buyerId);
}
