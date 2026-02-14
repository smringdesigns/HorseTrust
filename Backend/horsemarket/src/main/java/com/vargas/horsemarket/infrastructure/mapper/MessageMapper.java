package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Message;
import com.vargas.horsemarket.infrastructure.persistence.entity.MessageEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MessageMapper {
    Message toDomain(MessageEntity entity);
    MessageEntity toEntity(Message domain);
}
