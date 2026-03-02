package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.VeterinaryRecord;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import com.vargas.horsemarket.infrastructure.persistence.entity.VeterinaryRecordEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class VeterinaryRecordMapperImpl implements VeterinaryRecordMapper {

    @Override
    public VeterinaryRecord toDomain(VeterinaryRecordEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VeterinaryRecord.VeterinaryRecordBuilder veterinaryRecord = VeterinaryRecord.builder();

        veterinaryRecord.listingId( entityListingId( entity ) );
        veterinaryRecord.id( entity.getId() );
        veterinaryRecord.vetId( entity.getVetId() );
        veterinaryRecord.status( entity.getStatus() );
        veterinaryRecord.generalObservations( entity.getGeneralObservations() );
        veterinaryRecord.diagnoses( entity.getDiagnoses() );
        veterinaryRecord.vaccines( entity.getVaccines() );
        veterinaryRecord.lastVaccinationDate( entity.getLastVaccinationDate() );
        veterinaryRecord.lastCheckupDate( entity.getLastCheckupDate() );
        veterinaryRecord.healthCertificateUrl( entity.getHealthCertificateUrl() );
        veterinaryRecord.rejectionReason( entity.getRejectionReason() );
        veterinaryRecord.approvedAt( entity.getApprovedAt() );
        veterinaryRecord.rejectedAt( entity.getRejectedAt() );
        veterinaryRecord.createdAt( entity.getCreatedAt() );
        veterinaryRecord.updatedAt( entity.getUpdatedAt() );
        veterinaryRecord.createdBy( entity.getCreatedBy() );
        veterinaryRecord.updatedBy( entity.getUpdatedBy() );

        return veterinaryRecord.build();
    }

    @Override
    public VeterinaryRecordEntity toEntity(VeterinaryRecord domain) {
        if ( domain == null ) {
            return null;
        }

        VeterinaryRecordEntity.VeterinaryRecordEntityBuilder veterinaryRecordEntity = VeterinaryRecordEntity.builder();

        veterinaryRecordEntity.id( domain.getId() );
        veterinaryRecordEntity.vetId( domain.getVetId() );
        veterinaryRecordEntity.status( domain.getStatus() );
        veterinaryRecordEntity.generalObservations( domain.getGeneralObservations() );
        veterinaryRecordEntity.diagnoses( domain.getDiagnoses() );
        veterinaryRecordEntity.vaccines( domain.getVaccines() );
        veterinaryRecordEntity.lastVaccinationDate( domain.getLastVaccinationDate() );
        veterinaryRecordEntity.lastCheckupDate( domain.getLastCheckupDate() );
        veterinaryRecordEntity.healthCertificateUrl( domain.getHealthCertificateUrl() );
        veterinaryRecordEntity.rejectionReason( domain.getRejectionReason() );
        veterinaryRecordEntity.approvedAt( domain.getApprovedAt() );
        veterinaryRecordEntity.rejectedAt( domain.getRejectedAt() );

        return veterinaryRecordEntity.build();
    }

    private Long entityListingId(VeterinaryRecordEntity veterinaryRecordEntity) {
        if ( veterinaryRecordEntity == null ) {
            return null;
        }
        HorseListingEntity listing = veterinaryRecordEntity.getListing();
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
