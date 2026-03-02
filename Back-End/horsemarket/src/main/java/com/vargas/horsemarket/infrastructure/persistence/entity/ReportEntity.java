package com.vargas.horsemarket.infrastructure.persistence.entity;

import com.vargas.horsemarket.domain.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ReportEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reported_by", nullable = false)
    private Long reportedBy;

    @Column(name = "reported_listing_id")
    private Long reportedListingId;

    @Column(name = "reported_user_id")
    private Long reportedUserId;

    @Column(nullable = false, length = 200)
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(nullable = false, columnDefinition =
            "VARCHAR(20)")
    @Builder.Default
    private ReportStatus status = ReportStatus.OPEN;

    @Column(name = "admin_notes", columnDefinition = "TEXT")
    private String adminNotes;

    @Column(name = "reviewed_by")
    private Long reviewedBy;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
}
