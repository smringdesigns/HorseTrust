package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.enums.ReportStatus;
import com.vargas.horsemarket.domain.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Puerto de dominio para persistencia de reportes.
 */
public interface ReportRepository {

    Report save(Report report);

    Optional<Report> findById(Long id);

    Page<Report> findByStatus(ReportStatus status, Pageable pageable);

    long countOpenReportsByUserId(Long userId);
}
