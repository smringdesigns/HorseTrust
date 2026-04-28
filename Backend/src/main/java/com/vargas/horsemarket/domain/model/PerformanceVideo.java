package com.vargas.horsemarket.domain.model;

import com.vargas.horsemarket.domain.enums.VideoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Video de rendimiento del caballo asociado a un anuncio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceVideo {

    private Long id;
    private Long listingId;
    private String title;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private VideoStatus status;
    private String rejectionReason;
    private Long uploadedBy;
    private Long reviewedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---- Reglas de negocio ----

    public boolean canBeApproved() {
        return status == VideoStatus.PENDING;
    }

    public void approve(Long reviewerId) {
        if (!canBeApproved()) {
            throw new IllegalStateException("El video no puede ser aprobado en su estado: " + status);
        }
        this.status = VideoStatus.APPROVED;
        this.reviewedBy = reviewerId;
        this.reviewedAt = LocalDateTime.now();
    }

    public void reject(Long reviewerId, String reason) {
        if (status != VideoStatus.PENDING) {
            throw new IllegalStateException("El video no puede ser rechazado en su estado: " + status);
        }
        this.status = VideoStatus.REJECTED;
        this.rejectionReason = reason;
        this.reviewedBy = reviewerId;
        this.reviewedAt = LocalDateTime.now();
    }
}
