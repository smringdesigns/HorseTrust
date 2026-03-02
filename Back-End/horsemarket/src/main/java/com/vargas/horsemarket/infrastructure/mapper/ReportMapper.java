package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Report;
import com.vargas.horsemarket.infrastructure.persistence.entity.ReportEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReportMapper {
    Report toDomain(ReportEntity entity);
    ReportEntity toEntity(Report domain);
}
