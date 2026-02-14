package com.vargas.horsemarket.infrastructure.persistence.adapter;

import com.vargas.horsemarket.domain.model.VeterinaryRecord;
import com.vargas.horsemarket.domain.repository.VeterinaryRecordRepository;
import com.vargas.horsemarket.infrastructure.mapper.VeterinaryRecordMapper;
import com.vargas.horsemarket.infrastructure.persistence.repository.VeterinaryRecordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VeterinaryRecordRepositoryAdapter implements VeterinaryRecordRepository {

    private final VeterinaryRecordJpaRepository jpaRepository;
    private final VeterinaryRecordMapper mapper;

    @Override
    public VeterinaryRecord save(VeterinaryRecord record) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(record)));
    }

    @Override
    public Optional<VeterinaryRecord> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<VeterinaryRecord> findByListingId(Long listingId) {
        return jpaRepository.findByListingId(listingId).map(mapper::toDomain);
    }

    @Override
    public List<VeterinaryRecord> findByVetId(Long vetId) {
        return jpaRepository.findByVetId(vetId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
