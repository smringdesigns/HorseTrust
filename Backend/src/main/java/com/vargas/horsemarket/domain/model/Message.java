package com.vargas.horsemarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Mensaje en el chat interno comprador-vendedor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;
    private Long listingId;
    private Long senderId;
    private Long receiverId;
    private String content;
    private boolean read;
    private LocalDateTime readAt;
    private LocalDateTime createdAt;

    // ---- Reglas de negocio ----

    public boolean canBeReadBy(Long userId) {
        return receiverId.equals(userId);
    }

    public void markAsRead() {
        if (!read) {
            this.read = true;
            this.readAt = LocalDateTime.now();
        }
    }
}
