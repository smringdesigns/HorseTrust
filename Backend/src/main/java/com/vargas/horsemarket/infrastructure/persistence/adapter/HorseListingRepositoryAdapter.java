package com.vargas.horsemarket.infrastructure.persistence.adapter;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.infrastructure.mapper.HorseListingMapper;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import com.vargas.horsemarket.infrastructure.persistence.repository.HorseListingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HorseListingRepositoryAdapter implements HorseListingRepository {

    private final HorseListingJpaRepository jpaRepository;
    private final HorseListingMapper mapper;

    @Override
    public HorseListing save(HorseListing listing) {
        HorseListingEntity entity = mapper.toEntity(listing);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<HorseListing> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<HorseListing> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Page<HorseListing> findByStatus(ListingStatus status, Pageable pageable) {
        return jpaRepository.findByStatus(status, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<HorseListing> findBySellerId(Long sellerId, Pageable pageable) {
        return jpaRepository.findBySellerId(sellerId, pageable).map(mapper::toDomain);
    }

    @Override
    public Page<HorseListing> findByBreedContainingIgnoreCase(String breed, Pageable pageable) {
        return jpaRepository.findByBreedContainingIgnoreCase(breed, pageable).map(mapper::toDomain);
    }

    @Override
    public List<HorseListing> findBySellerIdAndStatus(Long sellerId, ListingStatus status) {
        return jpaRepository.findBySellerIdAndStatus(sellerId, status)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
