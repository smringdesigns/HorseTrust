package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Rating;
import com.vargas.horsemarket.infrastructure.persistence.entity.RatingEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RatingMapper {
    Rating toDomain(RatingEntity entity);
    RatingEntity toEntity(Rating domain);
}
