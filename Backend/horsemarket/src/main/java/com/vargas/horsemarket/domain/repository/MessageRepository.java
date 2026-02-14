package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de dominio para persistencia de mensajes.
 */
public interface MessageRepository {

    Message save(Message message);

    Optional<Message> findById(Long id);

    Page<Message> findByListingIdAndParticipants(Long listingId, Long userId1, Long userId2, Pageable pageable);

    List<Message> findUnreadByReceiverId(Long receiverId);

    long countUnreadByReceiverId(Long receiverId);
}
