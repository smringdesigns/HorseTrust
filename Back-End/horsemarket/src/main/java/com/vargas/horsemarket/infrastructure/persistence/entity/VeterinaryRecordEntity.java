package com.vargas.horsemarket.infrastructure.persistence.entity;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import com.vargas.horsemarket.domain.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad JPA para la tabla veterinary_records.
 */
@Entity
@Table(name = "veterinary_records")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeterinaryRecordEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    private HorseListingEntity listing;

    @Column(name = "vet_id", nullable = false)
    private Long vetId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(nullable = false, columnDefinition =
            "VARCHAR(30)")
    @Builder.Default
    private VerificationStatus status = VerificationStatus.PENDING;


    @Column(name = "general_observations", columnDefinition = "TEXT")
    private String generalObservations;

    @Column(columnDefinition = "TEXT")
    private String diagnoses;

    @Column(columnDefinition = "TEXT")
    private String vaccines;

    @Column(name = "last_vaccination_date")
    private LocalDate lastVaccinationDate;

    @Column(name = "last_checkup_date")
    private LocalDate lastCheckupDate;

    @Column(name = "health_certificate_url")
    private String healthCertificateUrl;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "rejected_at")
    private LocalDateTime rejectedAt;
}
