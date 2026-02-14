package com.vargas.horsemarket.infrastructure.persistence.repository;

import com.vargas.horsemarket.domain.enums.ReportStatus;
import com.vargas.horsemarket.infrastructure.persistence.entity.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJpaRepository extends JpaRepository<ReportEntity, Long> {
    Page<ReportEntity> findByStatus(ReportStatus status, Pageable pageable);

    @Query("SELECT COUNT(r) FROM ReportEntity r WHERE r.reportedUserId = :userId AND r.status = 'OPEN'")
    long countOpenByReportedUserId(@Param("userId") Long userId);
}
