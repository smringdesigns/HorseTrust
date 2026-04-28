package com.vargas.horsemarket.application.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
    private String profileImageUrl;
    private Set<String> roles;
    private boolean verified;
    private boolean active;
    private Double averageRating;
    private Integer totalSales;
    private LocalDateTime createdAt;
}
