package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.AuthResponse;
import com.vargas.horsemarket.application.dto.LoginRequest;
import com.vargas.horsemarket.application.dto.RegisterRequest;
import com.vargas.horsemarket.application.usecase.AuthUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación.
 * Solo delega a casos de uso — sin lógica de negocio aquí.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Registro, login y refresh de tokens JWT")
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    @Operation(summary = "Registrar nuevo usuario",
               description = "Crea una nueva cuenta con uno o más roles (BUYER, SELLER, VET, ADMIN)")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.debug("POST /auth/register - email: {}", request.getEmail());
        AuthResponse response = authUseCase.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión",
               description = "Retorna JWT de acceso y refresh token")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        log.debug("POST /auth/login - email: {}", request.getEmail());
        return ResponseEntity.ok(authUseCase.login(request));
    }
}
