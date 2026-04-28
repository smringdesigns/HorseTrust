package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.VeterinaryRecord;
import com.vargas.horsemarket.infrastructure.persistence.entity.VeterinaryRecordEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VeterinaryRecordMapper {

    @Mapping(target = "listingId", source = "listing.id")
    VeterinaryRecord toDomain(VeterinaryRecordEntity entity);

    @Mapping(target = "listing", ignore = true)
    VeterinaryRecordEntity toEntity(VeterinaryRecord domain);
}
