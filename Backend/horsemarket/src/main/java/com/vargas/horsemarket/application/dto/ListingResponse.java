package com.vargas.horsemarket.application.dto;

import com.vargas.horsemarket.domain.enums.ListingStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ListingResponse {
    private Long id;
    private String title;
    private String description;
    private String horseName;
    private String breed;
    private Integer ageYears;
    private String sex;
    private String discipline;
    private String level;
    private BigDecimal price;
    private String currencyCode;
    private String location;
    private ListingStatus status;
    private boolean premium;
    private Long sellerId;
    private String sellerName;
    private List<String> imageUrls;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
}
