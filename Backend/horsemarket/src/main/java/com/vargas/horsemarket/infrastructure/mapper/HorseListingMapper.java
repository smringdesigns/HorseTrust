package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.valueobject.Money;
import com.vargas.horsemarket.infrastructure.persistence.entity.HorseListingEntity;
import org.mapstruct.*;

import java.math.BigDecimal;

/**
 * MapStruct mapper para HorseListing.
 * Maneja conversión especial del Value Object Money.
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {VeterinaryRecordMapper.class, PerformanceVideoMapper.class})
public interface HorseListingMapper {

    @Mapping(target = "price", expression = "java(mapMoney(entity))")
    HorseListing toDomain(HorseListingEntity entity);

    @Mapping(target = "price", expression = "java(domain.getPrice() != null ? domain.getPrice().getAmount() : null)")
    @Mapping(target = "currencyCode", expression = "java(domain.getPrice() != null ? domain.getPrice().getCurrencyCode() : \"MXN\")")
    HorseListingEntity toEntity(HorseListing domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "currencyCode", ignore = true)
    void updateEntityFromDomain(HorseListing domain, @MappingTarget HorseListingEntity entity);

    default Money mapMoney(HorseListingEntity entity) {
        if (entity.getPrice() == null) return null;
        String currency = entity.getCurrencyCode() != null ? entity.getCurrencyCode() : "MXN";
        return Money.of(entity.getPrice(), currency);
    }
}
