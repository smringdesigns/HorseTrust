package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.UserProfileResponse;
import com.vargas.horsemarket.domain.model.User;
import com.vargas.horsemarket.domain.repository.UserRepository;
import com.vargas.horsemarket.shared.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Perfiles y gestión de usuarios")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Obtener perfil del usuario autenticado")
    public ResponseEntity<UserProfileResponse> getMyProfile(Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return ResponseEntity.ok(toProfileResponse(user));
    }

    @GetMapping("/{id}/profile")
    @Operation(summary = "Ver perfil público de un usuario")
    public ResponseEntity<UserProfileResponse> getPublicProfile(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        return ResponseEntity.ok(toProfileResponse(user));
    }

    private UserProfileResponse toProfileResponse(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .location(user.getLocation())
                .profileImageUrl(user.getProfileImageUrl())
                .roles(user.getRoles() != null
                        ? user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
                        : null)
                .verified(user.isVerified())
                .active(user.isActive())
                .averageRating(user.getAverageRating())
                .totalSales(user.getTotalSales())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
