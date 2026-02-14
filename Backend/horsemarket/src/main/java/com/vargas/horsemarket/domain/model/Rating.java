package com.vargas.horsemarket.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Calificación post-venta de un vendedor.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private Long id;
    private Long listingId;
    private Long buyerId;
    private Long sellerId;
    private Integer score;       // 1 a 5
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---- Reglas de negocio ----

    public boolean isValidScore() {
        return score != null && score >= 1 && score <= 5;
    }

    public void validateBeforeSave() {
        if (!isValidScore()) {
            throw new IllegalArgumentException("El puntaje debe ser entre 1 y 5");
        }
        if (buyerId.equals(sellerId)) {
            throw new IllegalArgumentException("No puedes calificarte a ti mismo");
        }
    }
}
