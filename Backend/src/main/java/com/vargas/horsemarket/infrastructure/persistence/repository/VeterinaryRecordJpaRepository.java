package com.vargas.horsemarket.infrastructure.persistence.repository;

import com.vargas.horsemarket.infrastructure.persistence.entity.VeterinaryRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinaryRecordJpaRepository extends JpaRepository<VeterinaryRecordEntity, Long> {
    Optional<VeterinaryRecordEntity> findByListingId(Long listingId);
    List<VeterinaryRecordEntity> findByVetId(Long vetId);
}
