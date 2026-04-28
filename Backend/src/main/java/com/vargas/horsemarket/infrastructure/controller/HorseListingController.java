package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.CreateListingRequest;
import com.vargas.horsemarket.application.dto.ListingResponse;
import com.vargas.horsemarket.application.dto.PageResponse;
import com.vargas.horsemarket.application.usecase.CreateListingUseCase;
import com.vargas.horsemarket.application.usecase.ListingQueryUseCase;
import com.vargas.horsemarket.application.usecase.PublishListingUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para anuncios de caballos.
 * Solo orquesta llamadas a casos de uso.
 */
@Slf4j
@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
@Tag(name = "Anuncios", description = "Gestión de anuncios de caballos")
public class HorseListingController {

    private final CreateListingUseCase createListingUseCase;
    private final PublishListingUseCase publishListingUseCase;
    private final ListingQueryUseCase listingQueryUseCase;

    @GetMapping
    @Operation(summary = "Listar anuncios publicados (paginado)")
    public ResponseEntity<PageResponse<ListingResponse>> getPublishedListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingQueryUseCase.findPublishedListings(page, size));
    }

    @GetMapping("/verified")
    @Operation(summary = "Listar anuncios verificados veterinariamente")
    public ResponseEntity<PageResponse<ListingResponse>> getVerifiedListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingQueryUseCase.findVerifiedListings(page, size));
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar anuncios por raza")
    public ResponseEntity<PageResponse<ListingResponse>> searchByBreed(
            @RequestParam String breed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingQueryUseCase.findByBreed(breed, page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener anuncio por ID")
    public ResponseEntity<ListingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(listingQueryUseCase.findById(id));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('SELLER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Mis anuncios (vendedor autenticado)")
    public ResponseEntity<PageResponse<ListingResponse>> getMyListings(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingQueryUseCase.findMyListings(authentication.getName(), page, size));
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Crear nuevo anuncio (solo SELLER)")
    public ResponseEntity<ListingResponse> createListing(@Valid @RequestBody CreateListingRequest request) {
        log.debug("POST /listings - Creando nuevo anuncio");
        ListingResponse response = createListingUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/publish")
    @PreAuthorize("hasRole('SELLER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Publicar anuncio (DRAFT → PUBLISHED)")
    public ResponseEntity<ListingResponse> publishListing(@PathVariable Long id) {
        log.debug("PATCH /listings/{}/publish", id);
        return ResponseEntity.ok(publishListingUseCase.execute(id));
    }
}
