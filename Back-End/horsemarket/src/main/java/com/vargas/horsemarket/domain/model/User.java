package com.vargas.horsemarket.domain.model;

import com.vargas.horsemarket.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entidad de dominio pura - sin anotaciones JPA.
 * Representa a un usuario del marketplace.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String location;
    private String profileImageUrl;
    private Set<UserRole> roles;
    private boolean verified;
    private boolean active;
    private String verificationToken;
    private Double averageRating;
    private Integer totalSales;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // ---- Reglas de negocio del dominio ----

    public boolean isSeller() {
        return roles != null && roles.contains(UserRole.SELLER);
    }

    public boolean isVet() {
        return roles != null && roles.contains(UserRole.VET);
    }

    public boolean isAdmin() {
        return roles != null && roles.contains(UserRole.ADMIN);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean canCreateListing() {
        return isSeller() && active;
    }

    public boolean canVerifyListing() {
        return isVet() && active && verified;
    }
}
