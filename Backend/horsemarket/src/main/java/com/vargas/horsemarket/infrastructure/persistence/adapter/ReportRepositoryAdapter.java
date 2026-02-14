package com.vargas.horsemarket.infrastructure.persistence.adapter;

import com.vargas.horsemarket.domain.enums.ReportStatus;
import com.vargas.horsemarket.domain.model.Report;
import com.vargas.horsemarket.domain.repository.ReportRepository;
import com.vargas.horsemarket.infrastructure.mapper.ReportMapper;
import com.vargas.horsemarket.infrastructure.persistence.repository.ReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportRepositoryAdapter implements ReportRepository {

    private final ReportJpaRepository jpaRepository;
    private final ReportMapper mapper;

    @Override
    public Report save(Report report) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(report)));
    }

    @Override
    public Optional<Report> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Report> findByStatus(ReportStatus status, Pageable pageable) {
        return jpaRepository.findByStatus(status, pageable).map(mapper::toDomain);
    }

    @Override
    public long countOpenReportsByUserId(Long userId) {
        return jpaRepository.countOpenByReportedUserId(userId);
    }
}
