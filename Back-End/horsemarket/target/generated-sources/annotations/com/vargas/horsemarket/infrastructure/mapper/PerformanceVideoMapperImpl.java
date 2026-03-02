package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.PerformanceVideo;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import com.vargas.horsemarket.infrastructure.persistence.entity.PerformanceVideoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class PerformanceVideoMapperImpl implements PerformanceVideoMapper {

    @Override
    public PerformanceVideo toDomain(PerformanceVideoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PerformanceVideo.PerformanceVideoBuilder performanceVideo = PerformanceVideo.builder();

        performanceVideo.listingId( entityListingId( entity ) );
        performanceVideo.id( entity.getId() );
        performanceVideo.title( entity.getTitle() );
        performanceVideo.description( entity.getDescription() );
        performanceVideo.videoUrl( entity.getVideoUrl() );
        performanceVideo.thumbnailUrl( entity.getThumbnailUrl() );
        performanceVideo.status( entity.getStatus() );
        performanceVideo.rejectionReason( entity.getRejectionReason() );
        performanceVideo.uploadedBy( entity.getUploadedBy() );
        performanceVideo.reviewedBy( entity.getReviewedBy() );
        performanceVideo.reviewedAt( entity.getReviewedAt() );
        performanceVideo.createdAt( entity.getCreatedAt() );
        performanceVideo.updatedAt( entity.getUpdatedAt() );

        return performanceVideo.build();
    }

    @Override
    public PerformanceVideoEntity toEntity(PerformanceVideo domain) {
        if ( domain == null ) {
            return null;
        }

        PerformanceVideoEntity.PerformanceVideoEntityBuilder performanceVideoEntity = PerformanceVideoEntity.builder();

        performanceVideoEntity.id( domain.getId() );
        performanceVideoEntity.title( domain.getTitle() );
        performanceVideoEntity.description( domain.getDescription() );
        performanceVideoEntity.videoUrl( domain.getVideoUrl() );
        performanceVideoEntity.thumbnailUrl( domain.getThumbnailUrl() );
        performanceVideoEntity.status( domain.getStatus() );
        performanceVideoEntity.rejectionReason( domain.getRejectionReason() );
        performanceVideoEntity.uploadedBy( domain.getUploadedBy() );
        performanceVideoEntity.reviewedBy( domain.getReviewedBy() );
        performanceVideoEntity.reviewedAt( domain.getReviewedAt() );

        return performanceVideoEntity.build();
    }

    private Long entityListingId(PerformanceVideoEntity performanceVideoEntity) {
        if ( performanceVideoEntity == null ) {
            return null;
        }
        HorseListingEntity listing = performanceVideoEntity.getListing();
        if ( listing == null ) {
            return null;
        }
        Long id = listing.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
