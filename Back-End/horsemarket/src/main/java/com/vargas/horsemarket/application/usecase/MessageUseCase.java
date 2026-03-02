package com.vargas.horsemarket.application.usecase;

import com.vargas.horsemarket.application.dto.MessageResponse;
import com.vargas.horsemarket.application.dto.PageResponse;
import com.vargas.horsemarket.application.dto.SendMessageRequest;
import com.vargas.horsemarket.domain.model.HorseListing;
import com.vargas.horsemarket.domain.model.Message;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.HorseListingRepository;
import com.vargas.horsemarket.domain.repository.MessageRepository;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.shared.exception.BusinessRuleException;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Caso de uso: Mensajería segura comprador-vendedor.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageUseCase {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final HorseListingRepository listingRepository;

    @Transactional
    public MessageResponse sendMessage(SendMessageRequest request) {
        String senderEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Remitente no encontrado"));

        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Destinatario", request.getReceiverId()));

        HorseListing listing = listingRepository.findById(request.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("HorseListing", request.getListingId()));

        // Evitar mensajes a uno mismo
        if (sender.getId().equals(receiver.getId())) {
            throw new BusinessRuleException("No puedes enviarte mensajes a ti mismo");
        }

        // Solo comprador o vendedor pueden participar en el chat del anuncio
        boolean isSeller = listing.getSellerId().equals(sender.getId())
                || listing.getSellerId().equals(receiver.getId());
        if (!isSeller) {
            throw new BusinessRuleException("Solo el comprador y el vendedor pueden intercambiar mensajes sobre este anuncio");
        }

        Message message = Message.builder()
                .listingId(listing.getId())
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .content(request.getContent())
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        Message saved = messageRepository.save(message);
        log.info("Mensaje enviado de {} a {} para anuncio {}", sender.getId(), receiver.getId(), listing.getId());

        return toResponse(saved, sender);
    }

    @Transactional(readOnly = true)
    public PageResponse<MessageResponse> getConversation(Long listingId, Long otherUserId, int page, int size) {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        Page<Message> messages = messageRepository.findByListingIdAndParticipants(
                listingId, currentUser.getId(), otherUserId, pageable);

        return PageResponse.<MessageResponse>builder()
                .content(messages.getContent().stream()
                        .map(m -> {
                            User sender = userRepository.findById(m.getSenderId()).orElse(null);
                            return toResponse(m, sender);
                        })
                        .toList())
                .page(messages.getNumber())
                .size(messages.getSize())
                .totalElements(messages.getTotalElements())
                .totalPages(messages.getTotalPages())
                .last(messages.isLast())
                .build();
    }

    private MessageResponse toResponse(Message message, User sender) {
        return MessageResponse.builder()
                .id(message.getId())
                .listingId(message.getListingId())
                .senderId(message.getSenderId())
                .senderName(sender != null ? sender.getFullName() : "Desconocido")
                .receiverId(message.getReceiverId())
                .content(message.getContent())
                .read(message.isRead())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
