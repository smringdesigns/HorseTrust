package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.PerformanceVideo;
import com.vargas.horsemarket.infrastructure.persistence.entity.PerformanceVideoEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PerformanceVideoMapper {

    @Mapping(target = "listingId", source = "listing.id")
    PerformanceVideo toDomain(PerformanceVideoEntity entity);

    @Mapping(target = "listing", ignore = true)
    PerformanceVideoEntity toEntity(PerformanceVideo domain);
}
