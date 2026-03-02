package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Rating;
import com.vargas.horsemarket.infrastructure.persistence.entity.RatingEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Override
    public Rating toDomain(RatingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Rating.RatingBuilder rating = Rating.builder();

        rating.id( entity.getId() );
        rating.listingId( entity.getListingId() );
        rating.buyerId( entity.getBuyerId() );
        rating.sellerId( entity.getSellerId() );
        rating.score( entity.getScore() );
        rating.comment( entity.getComment() );
        rating.createdAt( entity.getCreatedAt() );
        rating.updatedAt( entity.getUpdatedAt() );

        return rating.build();
    }

    @Override
    public RatingEntity toEntity(Rating domain) {
        if ( domain == null ) {
            return null;
        }

        RatingEntity.RatingEntityBuilder ratingEntity = RatingEntity.builder();

        ratingEntity.id( domain.getId() );
        ratingEntity.listingId( domain.getListingId() );
        ratingEntity.buyerId( domain.getBuyerId() );
        ratingEntity.sellerId( domain.getSellerId() );
        ratingEntity.score( domain.getScore() );
        ratingEntity.comment( domain.getComment() );

        return ratingEntity.build();
    }
}
