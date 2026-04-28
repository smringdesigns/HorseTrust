package com.vargas.horsemarket.infrastructure.persistence.adapter;

import com.vargas.horsemarket.domain.model.Rating;
import com.vargas.horsemarket.domain.repository.RatingRepository;
import com.vargas.horsemarket.infrastructure.mapper.RatingMapper;
import com.vargas.horsemarket.infrastructure.persistence.repository.RatingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RatingRepositoryAdapter implements RatingRepository {

    private final RatingJpaRepository jpaRepository;
    private final RatingMapper mapper;

    @Override
    public Rating save(Rating rating) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(rating)));
    }

    @Override
    public Optional<Rating> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Rating> findBySellerId(Long sellerId) {
        return jpaRepository.findBySellerId(sellerId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Rating> findByListingIdAndBuyerId(Long listingId, Long buyerId) {
        return jpaRepository.findByListingIdAndBuyerId(listingId, buyerId).map(mapper::toDomain);
    }

    @Override
    public Double calculateAverageRatingBySellerId(Long sellerId) {
        return jpaRepository.calculateAverageBySellerId(sellerId);
    }

    @Override
    public boolean existsByListingIdAndBuyerId(Long listingId, Long buyerId) {
        return jpaRepository.existsByListingIdAndBuyerId(listingId, buyerId);
    }
}
