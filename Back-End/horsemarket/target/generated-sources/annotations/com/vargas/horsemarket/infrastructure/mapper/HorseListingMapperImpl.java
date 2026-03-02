package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.PerformanceVideo;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import com.vargas.horsemarket.infrastructure.persistence.entity.PerformanceVideoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class HorseListingMapperImpl implements HorseListingMapper {

    @Autowired
    private VeterinaryRecordMapper veterinaryRecordMapper;
    @Autowired
    private PerformanceVideoMapper performanceVideoMapper;

    @Override
    public HorseListing toDomain(HorseListingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        HorseListing.HorseListingBuilder horseListing = HorseListing.builder();

        horseListing.id( entity.getId() );
        horseListing.title( entity.getTitle() );
        horseListing.description( entity.getDescription() );
        horseListing.horseName( entity.getHorseName() );
        horseListing.breed( entity.getBreed() );
        horseListing.ageYears( entity.getAgeYears() );
        horseListing.sex( entity.getSex() );
        horseListing.discipline( entity.getDiscipline() );
        horseListing.level( entity.getLevel() );
        horseListing.location( entity.getLocation() );
        horseListing.status( entity.getStatus() );
        horseListing.premium( entity.isPremium() );
        horseListing.sellerId( entity.getSellerId() );
        List<String> list = entity.getImageUrls();
        if ( list != null ) {
            horseListing.imageUrls( new ArrayList<String>( list ) );
        }
        horseListing.videos( performanceVideoEntityListToPerformanceVideoList( entity.getVideos() ) );
        horseListing.veterinaryRecord( veterinaryRecordMapper.toDomain( entity.getVeterinaryRecord() ) );
        horseListing.publishedAt( entity.getPublishedAt() );
        horseListing.soldAt( entity.getSoldAt() );
        horseListing.createdAt( entity.getCreatedAt() );
        horseListing.updatedAt( entity.getUpdatedAt() );
        horseListing.createdBy( entity.getCreatedBy() );
        horseListing.updatedBy( entity.getUpdatedBy() );

        horseListing.price( mapMoney(entity) );

        return horseListing.build();
    }

    @Override
    public HorseListingEntity toEntity(HorseListing domain) {
        if ( domain == null ) {
            return null;
        }

        HorseListingEntity.HorseListingEntityBuilder horseListingEntity = HorseListingEntity.builder();

        horseListingEntity.id( domain.getId() );
        horseListingEntity.title( domain.getTitle() );
        horseListingEntity.description( domain.getDescription() );
        horseListingEntity.horseName( domain.getHorseName() );
        horseListingEntity.breed( domain.getBreed() );
        horseListingEntity.ageYears( domain.getAgeYears() );
        horseListingEntity.sex( domain.getSex() );
        horseListingEntity.discipline( domain.getDiscipline() );
        horseListingEntity.level( domain.getLevel() );
        horseListingEntity.location( domain.getLocation() );
        horseListingEntity.status( domain.getStatus() );
        horseListingEntity.premium( domain.isPremium() );
        horseListingEntity.sellerId( domain.getSellerId() );
        List<String> list = domain.getImageUrls();
        if ( list != null ) {
            horseListingEntity.imageUrls( new ArrayList<String>( list ) );
        }
        horseListingEntity.publishedAt( domain.getPublishedAt() );
        horseListingEntity.soldAt( domain.getSoldAt() );
        horseListingEntity.veterinaryRecord( veterinaryRecordMapper.toEntity( domain.getVeterinaryRecord() ) );
        horseListingEntity.videos( performanceVideoListToPerformanceVideoEntityList( domain.getVideos() ) );

        horseListingEntity.price( domain.getPrice() != null ? domain.getPrice().getAmount() : null );
        horseListingEntity.currencyCode( domain.getPrice() != null ? domain.getPrice().getCurrencyCode() : "MXN" );

        return horseListingEntity.build();
    }

    @Override
    public void updateEntityFromDomain(HorseListing domain, HorseListingEntity entity) {
        if ( domain == null ) {
            return;
        }

        if ( domain.getCreatedAt() != null ) {
            entity.setCreatedAt( domain.getCreatedAt() );
        }
        if ( domain.getUpdatedAt() != null ) {
            entity.setUpdatedAt( domain.getUpdatedAt() );
        }
        if ( domain.getCreatedBy() != null ) {
            entity.setCreatedBy( domain.getCreatedBy() );
        }
        if ( domain.getUpdatedBy() != null ) {
            entity.setUpdatedBy( domain.getUpdatedBy() );
        }
        if ( domain.getId() != null ) {
            entity.setId( domain.getId() );
        }
        if ( domain.getTitle() != null ) {
            entity.setTitle( domain.getTitle() );
        }
        if ( domain.getDescription() != null ) {
            entity.setDescription( domain.getDescription() );
        }
        if ( domain.getHorseName() != null ) {
            entity.setHorseName( domain.getHorseName() );
        }
        if ( domain.getBreed() != null ) {
            entity.setBreed( domain.getBreed() );
        }
        if ( domain.getAgeYears() != null ) {
            entity.setAgeYears( domain.getAgeYears() );
        }
        if ( domain.getSex() != null ) {
            entity.setSex( domain.getSex() );
        }
        if ( domain.getDiscipline() != null ) {
            entity.setDiscipline( domain.getDiscipline() );
        }
        if ( domain.getLevel() != null ) {
            entity.setLevel( domain.getLevel() );
        }
        if ( domain.getLocation() != null ) {
            entity.setLocation( domain.getLocation() );
        }
        if ( domain.getStatus() != null ) {
            entity.setStatus( domain.getStatus() );
        }
        entity.setPremium( domain.isPremium() );
        if ( domain.getSellerId() != null ) {
            entity.setSellerId( domain.getSellerId() );
        }
        if ( entity.getImageUrls() != null ) {
            List<String> list = domain.getImageUrls();
            if ( list != null ) {
                entity.getImageUrls().clear();
                entity.getImageUrls().addAll( list );
            }
        }
        else {
            List<String> list = domain.getImageUrls();
            if ( list != null ) {
                entity.setImageUrls( new ArrayList<String>( list ) );
            }
        }
        if ( domain.getPublishedAt() != null ) {
            entity.setPublishedAt( domain.getPublishedAt() );
        }
        if ( domain.getSoldAt() != null ) {
            entity.setSoldAt( domain.getSoldAt() );
        }
        if ( domain.getVeterinaryRecord() != null ) {
            entity.setVeterinaryRecord( veterinaryRecordMapper.toEntity( domain.getVeterinaryRecord() ) );
        }
        if ( entity.getVideos() != null ) {
            List<PerformanceVideoEntity> list1 = performanceVideoListToPerformanceVideoEntityList( domain.getVideos() );
            if ( list1 != null ) {
                entity.getVideos().clear();
                entity.getVideos().addAll( list1 );
            }
        }
        else {
            List<PerformanceVideoEntity> list1 = performanceVideoListToPerformanceVideoEntityList( domain.getVideos() );
            if ( list1 != null ) {
                entity.setVideos( list1 );
            }
        }
    }

    protected List<PerformanceVideo> performanceVideoEntityListToPerformanceVideoList(List<PerformanceVideoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PerformanceVideo> list1 = new ArrayList<PerformanceVideo>( list.size() );
        for ( PerformanceVideoEntity performanceVideoEntity : list ) {
            list1.add( performanceVideoMapper.toDomain( performanceVideoEntity ) );
        }

        return list1;
    }

    protected List<PerformanceVideoEntity> performanceVideoListToPerformanceVideoEntityList(List<PerformanceVideo> list) {
        if ( list == null ) {
            return null;
        }

        List<PerformanceVideoEntity> list1 = new ArrayList<PerformanceVideoEntity>( list.size() );
        for ( PerformanceVideo performanceVideo : list ) {
            list1.add( performanceVideoMapper.toEntity( performanceVideo ) );
        }

        return list1;
    }
}
