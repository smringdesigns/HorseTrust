package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.*;

/**
 * MapStruct mapper para convertir entre UserEntity (JPA) y User (dominio).
 */
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);
}
