package com.vargas.horsemarket.domain.model;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Representa un anuncio de venta de caballo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorseListing {

    private Long id;
    private String title;
    private String description;
    private String horseName;
    private String breed;
    private Integer ageYears;
    private String sex;         // MALE, FEMALE, GELDING
    private String discipline;  // DRESSAGE, JUMPING, ENDURANCE, TRAIL, etc.
    private String level;       // BEGINNER, INTERMEDIATE, ADVANCED, PROFESSIONAL
    private Money price;
    private String location;
    private ListingStatus status;
    private boolean premium;
    private Long sellerId;
    private List<String> imageUrls;
    private List<PerformanceVideo> videos;
    private VeterinaryRecord veterinaryRecord;
    private LocalDateTime publishedAt;
    private LocalDateTime soldAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // ---- Reglas de negocio del dominio ----

    public boolean canBePublished() {
        return status == ListingStatus.DRAFT && title != null && !title.isBlank();
    }

    public boolean canBeSentToVerification() {
        return status == ListingStatus.PUBLISHED;
    }

    public boolean canBeMarkedPremium() {
        return status == ListingStatus.VERIFIED;
    }

    public boolean canBeMarkedSold() {
        return status == ListingStatus.PUBLISHED || status == ListingStatus.VERIFIED;
    }

    public void publish() {
        if (!canBePublished()) {
            throw new IllegalStateException("El anuncio no puede publicarse en su estado actual: " + status);
        }
        this.status = ListingStatus.PUBLISHED;
        this.publishedAt = LocalDateTime.now();
    }

    public void sendToVerification() {
        if (!canBeSentToVerification()) {
            throw new IllegalStateException("El anuncio debe estar PUBLISHED para enviarlo a verificación");
        }
        this.status = ListingStatus.UNDER_VERIFICATION;
    }

    public void markAsVerified() {
        this.status = ListingStatus.VERIFIED;
    }

    public void markAsRejected() {
        this.status = ListingStatus.REJECTED;
    }

    public void markAsSold() {
        if (!canBeMarkedSold()) {
            throw new IllegalStateException("El anuncio no puede marcarse como vendido en su estado: " + status);
        }
        this.status = ListingStatus.SOLD;
        this.soldAt = LocalDateTime.now();
    }

    public void suspend() {
        this.status = ListingStatus.SUSPENDED;
    }

    public void enablePremium() {
        if (!canBeMarkedPremium()) {
            throw new IllegalStateException("Solo anuncios VERIFIED pueden ser marcados como Premium");
        }
        this.premium = true;
    }

    public List<String> getImageUrls() {
        if (imageUrls == null) imageUrls = new ArrayList<>();
        return imageUrls;
    }
}
