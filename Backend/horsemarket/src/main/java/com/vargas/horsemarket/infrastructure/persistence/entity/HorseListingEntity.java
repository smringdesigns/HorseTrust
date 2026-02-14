package com.vargas.horsemarket.infrastructure.persistence.entity;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA para la tabla horse_listings.
 */
@Entity
@Table(name = "horse_listings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorseListingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "horse_name", length = 100)
    private String horseName;

    @Column(length = 100)
    private String breed;

    @Column(name = "age_years")
    private Integer ageYears;

    @Column(length = 20)
    private String sex;

    @Column(length = 100)
    private String discipline;

    @Column(length = 50)
    private String level;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "currency_code", length = 10, nullable = false)
    @Builder.Default
    private String currencyCode = "MXN";

    @Column(length = 200)
    private String location;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 30)
//    @Builder.Default
//    private ListingStatus status = ListingStatus.DRAFT;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(Types.VARCHAR)
    @Column(nullable = false, columnDefinition =
    "VARCHAR(30)")
    @Builder.Default
    private ListingStatus status = ListingStatus.DRAFT;

    @Column(nullable = false)
    @Builder.Default
    private boolean premium = false;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @ElementCollection
    @CollectionTable(name = "listing_images", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "image_url")
    @Builder.Default
    private List<String> imageUrls = new ArrayList<>();

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "sold_at")
    private LocalDateTime soldAt;

    @OneToOne(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VeterinaryRecordEntity veterinaryRecord;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PerformanceVideoEntity> videos = new ArrayList<>();
}
