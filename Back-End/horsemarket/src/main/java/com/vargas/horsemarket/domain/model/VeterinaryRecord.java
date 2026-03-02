package com.vargas.horsemarket.domain.model;

import com.vargas.horsemarket.domain.enums.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Registro veterinario asociado a un anuncio de caballo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinaryRecord {

    private Long id;
    private Long listingId;
    private Long vetId;                    // Usuario con rol VET
    private VerificationStatus status;
    private String generalObservations;
    private String diagnoses;
    private String vaccines;               // Lista de vacunas aplicadas
    private LocalDate lastVaccinationDate;
    private LocalDate lastCheckupDate;
    private String healthCertificateUrl;
    private String rejectionReason;
    private LocalDateTime approvedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // ---- Reglas de negocio ----

    public boolean canBeApproved() {
        return status == VerificationStatus.IN_PROGRESS || status == VerificationStatus.PENDING;
    }

    public boolean canBeRejected() {
        return status == VerificationStatus.IN_PROGRESS || status == VerificationStatus.PENDING;
    }

    public void approve() {
        if (!canBeApproved()) {
            throw new IllegalStateException("El registro veterinario no puede ser aprobado en su estado: " + status);
        }
        this.status = VerificationStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        if (!canBeRejected()) {
            throw new IllegalStateException("El registro veterinario no puede ser rechazado en su estado: " + status);
        }
        this.status = VerificationStatus.REJECTED;
        this.rejectionReason = reason;
        this.rejectedAt = LocalDateTime.now();
    }

    public void startReview() {
        if (status != VerificationStatus.PENDING) {
            throw new IllegalStateException("Solo se puede iniciar revisión si está en estado PENDING");
        }
        this.status = VerificationStatus.IN_PROGRESS;
    }
}
