package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.CreateRatingRequest;
import com.vargas.horsemarket.application.usecase.RatingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Calificaciones", description = "Rating y reputación de vendedores")
public class RatingController {

    private final RatingUseCase ratingUseCase;

    @PostMapping
    @PreAuthorize("hasRole('BUYER')")
    @Operation(summary = "Calificar a un vendedor después de una compra")
    public ResponseEntity<Void> rateSeller(@Valid @RequestBody CreateRatingRequest request) {
        ratingUseCase.rateseller(request);
        return ResponseEntity.ok().build();
    }
}
