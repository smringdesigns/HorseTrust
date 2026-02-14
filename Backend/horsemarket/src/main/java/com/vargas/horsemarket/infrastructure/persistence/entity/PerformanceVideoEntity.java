package com.vargas.horsemarket.infrastructure.persistence.entity;

import com.vargas.horsemarket.domain.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_videos")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PerformanceVideoEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id", nullable = false)
    private HorseListingEntity listing;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 20)
//    @Builder.Default
//    private VideoStatus status = VideoStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(nullable = false, columnDefinition =
            "VARCHAR(20)")
    @Builder.Default
    private VideoStatus status = VideoStatus.PENDING;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @Column(name = "uploaded_by", nullable = false)
    private Long uploadedBy;

    @Column(name = "reviewed_by")
    private Long reviewedBy;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;
}
