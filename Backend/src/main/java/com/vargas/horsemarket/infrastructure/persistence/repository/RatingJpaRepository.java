package com.vargas.horsemarket.infrastructure.persistence.repository;

import com.vargas.horsemarket.infrastructure.persistence.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingJpaRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findBySellerId(Long sellerId);
    Optional<RatingEntity> findByListingIdAndBuyerId(Long listingId, Long buyerId);
    boolean existsByListingIdAndBuyerId(Long listingId, Long buyerId);

    @Query("SELECT AVG(r.score) FROM RatingEntity r WHERE r.sellerId = :sellerId")
    Double calculateAverageBySellerId(@Param("sellerId") Long sellerId);
}
