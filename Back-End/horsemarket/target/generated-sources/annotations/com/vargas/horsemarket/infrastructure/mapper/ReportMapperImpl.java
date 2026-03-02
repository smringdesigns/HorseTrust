package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Report;
import com.vargas.horsemarket.infrastructure.persistence.entity.ReportEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public Report toDomain(ReportEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Report.ReportBuilder report = Report.builder();

        report.id( entity.getId() );
        report.reportedBy( entity.getReportedBy() );
        report.reportedListingId( entity.getReportedListingId() );
        report.reportedUserId( entity.getReportedUserId() );
        report.reason( entity.getReason() );
        report.description( entity.getDescription() );
        report.status( entity.getStatus() );
        report.adminNotes( entity.getAdminNotes() );
        report.reviewedBy( entity.getReviewedBy() );
        report.reviewedAt( entity.getReviewedAt() );
        report.createdAt( entity.getCreatedAt() );
        report.updatedAt( entity.getUpdatedAt() );

        return report.build();
    }

    @Override
    public ReportEntity toEntity(Report domain) {
        if ( domain == null ) {
            return null;
        }

        ReportEntity.ReportEntityBuilder reportEntity = ReportEntity.builder();

        reportEntity.id( domain.getId() );
        reportEntity.reportedBy( domain.getReportedBy() );
        reportEntity.reportedListingId( domain.getReportedListingId() );
        reportEntity.reportedUserId( domain.getReportedUserId() );
        reportEntity.reason( domain.getReason() );
        reportEntity.description( domain.getDescription() );
        reportEntity.status( domain.getStatus() );
        reportEntity.adminNotes( domain.getAdminNotes() );
        reportEntity.reviewedBy( domain.getReviewedBy() );
        reportEntity.reviewedAt( domain.getReviewedAt() );

        return reportEntity.build();
    }
}
