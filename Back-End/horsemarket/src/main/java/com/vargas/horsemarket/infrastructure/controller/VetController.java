package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.CreateVetRecordRequest;
import com.vargas.horsemarket.application.dto.VetRecordResponse;
import com.vargas.horsemarket.application.dto.VetVerificationRequest;
import com.vargas.horsemarket.application.usecase.VetVerificationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para verificación veterinaria.
 * Acceso exclusivo para usuarios con rol VET.
 */
@Slf4j
@RestController
@RequestMapping("/vet")
@PreAuthorize("hasRole('VET')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Verificación Veterinaria", description = "Endpoints exclusivos para veterinarios")
public class VetController {

    private final VetVerificationUseCase vetVerificationUseCase;

    @PostMapping("/records")
    @Operation(summary = "Crear registro veterinario e iniciar revisión de un anuncio")
    public ResponseEntity<VetRecordResponse> createRecord(@Valid @RequestBody CreateVetRecordRequest request) {
        log.debug("POST /vet/records - listingId: {}", request.getListingId());
        VetRecordResponse response = vetVerificationUseCase.createRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/records/{recordId}/verify")
    @Operation(summary = "Aprobar o rechazar un anuncio (verificación final)")
    public ResponseEntity<VetRecordResponse> verify(
            @PathVariable Long recordId,
            @Valid @RequestBody VetVerificationRequest request) {
        log.debug("PATCH /vet/records/{}/verify - approved: {}", recordId, request.getApproved());
        return ResponseEntity.ok(vetVerificationUseCase.verify(recordId, request));
    }
}
