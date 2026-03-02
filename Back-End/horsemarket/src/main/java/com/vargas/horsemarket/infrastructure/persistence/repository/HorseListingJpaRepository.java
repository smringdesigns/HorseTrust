package com.vargas.horsemarket.infrastructure.persistence.repository;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorseListingJpaRepository extends JpaRepository<HorseListingEntity, Long> {
    Page<HorseListingEntity> findByStatus(ListingStatus status, Pageable pageable);
    Page<HorseListingEntity> findBySellerId(Long sellerId, Pageable pageable);
    Page<HorseListingEntity> findByBreedContainingIgnoreCase(String breed, Pageable pageable);
    List<HorseListingEntity> findBySellerIdAndStatus(Long sellerId, ListingStatus status);
}
