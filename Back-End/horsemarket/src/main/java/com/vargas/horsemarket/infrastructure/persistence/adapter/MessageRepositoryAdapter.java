package com.vargas.horsemarket.infrastructure.persistence.adapter;

import com.vargas.horsemarket.domain.model.Message;
import com.vargas.horsemarket.domain.repository.MessageRepository;
import com.vargas.horsemarket.infrastructure.mapper.MessageMapper;
import com.vargas.horsemarket.infrastructure.persistence.repository.MessageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageJpaRepository jpaRepository;
    private final MessageMapper mapper;

    @Override
    public Message save(Message message) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(message)));
    }

    @Override
    public Optional<Message> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Message> findByListingIdAndParticipants(Long listingId, Long userId1, Long userId2, Pageable pageable) {
        return jpaRepository.findConversation(listingId, userId1, userId2, pageable).map(mapper::toDomain);
    }

    @Override
    public List<Message> findUnreadByReceiverId(Long receiverId) {
        return jpaRepository.findByReceiverIdAndReadFalse(receiverId)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public long countUnreadByReceiverId(Long receiverId) {
        return jpaRepository.countByReceiverIdAndReadFalse(receiverId);
    }
}
