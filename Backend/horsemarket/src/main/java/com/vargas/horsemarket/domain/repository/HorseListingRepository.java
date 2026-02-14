package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de dominio para persistencia de anuncios de caballos.
 */
public interface HorseListingRepository {

    HorseListing save(HorseListing listing);

    Optional<HorseListing> findById(Long id);

    Page<HorseListing> findAll(Pageable pageable);

    Page<HorseListing> findByStatus(ListingStatus status, Pageable pageable);

    Page<HorseListing> findBySellerId(Long sellerId, Pageable pageable);

    Page<HorseListing> findByBreedContainingIgnoreCase(String breed, Pageable pageable);

    List<HorseListing> findBySellerIdAndStatus(Long sellerId, ListingStatus status);

    void deleteById(Long id);

    boolean existsById(Long id);
}
