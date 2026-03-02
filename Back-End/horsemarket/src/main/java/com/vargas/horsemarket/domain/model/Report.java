package com.vargas.horsemarket.domain.model;

import com.vargas.horsemarket.domain.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Reporte de anuncio o usuario fraudulento.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private Long id;
    private Long reportedBy;
    private Long reportedListingId;   // Puede ser null si es usuario
    private Long reportedUserId;      // Puede ser null si es anuncio
    private String reason;
    private String description;
    private ReportStatus status;
    private String adminNotes;
    private Long reviewedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---- Reglas de negocio ----

    public boolean isListingReport() {
        return reportedListingId != null;
    }

    public boolean isUserReport() {
        return reportedUserId != null;
    }

    public void resolve(Long adminId, String notes) {
        this.status = ReportStatus.RESOLVED;
        this.reviewedBy = adminId;
        this.adminNotes = notes;
        this.reviewedAt = LocalDateTime.now();
    }

    public void dismiss(Long adminId, String notes) {
        this.status = ReportStatus.DISMISSED;
        this.reviewedBy = adminId;
        this.adminNotes = notes;
        this.reviewedAt = LocalDateTime.now();
    }
}
