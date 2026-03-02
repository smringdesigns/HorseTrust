package com.vargas.horsemarket.infrastructure.persistence.repository;

import com.vargas.horsemarket.infrastructure.persistence.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m WHERE m.listingId = :listingId " +
           "AND ((m.senderId = :u1 AND m.receiverId = :u2) OR (m.senderId = :u2 AND m.receiverId = :u1)) " +
           "ORDER BY m.createdAt ASC")
    Page<MessageEntity> findConversation(@Param("listingId") Long listingId,
                                         @Param("u1") Long userId1,
                                         @Param("u2") Long userId2,
                                         Pageable pageable);

    List<MessageEntity> findByReceiverIdAndReadFalse(Long receiverId);
    long countByReceiverIdAndReadFalse(Long receiverId);
}
