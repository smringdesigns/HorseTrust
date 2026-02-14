package com.vargas.horsemarket.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ratings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"listing_id", "buyer_id"}))
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RatingEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listing_id", nullable = false)
    private Long listingId;

    @Column(name = "buyer_id", nullable = false)
    private Long buyerId;

    @Column(name = "seller_id", nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String comment;
}
