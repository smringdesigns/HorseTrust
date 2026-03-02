package com.vargas.horsemarket.infrastructure.mapper;

import com.vargas.horsemarket.domain.model.Message;
import com.vargas.horsemarket.infrastructure.persistence.entity.MessageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T23:18:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toDomain(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Message.MessageBuilder message = Message.builder();

        message.id( entity.getId() );
        message.listingId( entity.getListingId() );
        message.senderId( entity.getSenderId() );
        message.receiverId( entity.getReceiverId() );
        message.content( entity.getContent() );
        message.read( entity.isRead() );
        message.readAt( entity.getReadAt() );
        message.createdAt( entity.getCreatedAt() );

        return message.build();
    }

    @Override
    public MessageEntity toEntity(Message domain) {
        if ( domain == null ) {
            return null;
        }

        MessageEntity.MessageEntityBuilder messageEntity = MessageEntity.builder();

        messageEntity.id( domain.getId() );
        messageEntity.listingId( domain.getListingId() );
        messageEntity.senderId( domain.getSenderId() );
        messageEntity.receiverId( domain.getReceiverId() );
        messageEntity.content( domain.getContent() );
        messageEntity.read( domain.isRead() );
        messageEntity.readAt( domain.getReadAt() );

        return messageEntity.build();
    }
}
