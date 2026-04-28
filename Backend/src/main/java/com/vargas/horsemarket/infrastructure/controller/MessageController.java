package com.vargas.horsemarket.infrastructure.controller;

import com.vargas.horsemarket.application.dto.MessageResponse;
import com.vargas.horsemarket.application.dto.PageResponse;
import com.vargas.horsemarket.application.dto.SendMessageRequest;
import com.vargas.horsemarket.application.usecase.MessageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Mensajería", description = "Chat seguro comprador-vendedor")
public class MessageController {

    private final MessageUseCase messageUseCase;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Enviar mensaje sobre un anuncio")
    public ResponseEntity<MessageResponse> send(@Valid @RequestBody SendMessageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageUseCase.sendMessage(request));
    }

    @GetMapping("/conversation/{listingId}/{otherUserId}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Ver conversación con otro usuario sobre un anuncio")
    public ResponseEntity<PageResponse<MessageResponse>> getConversation(
            @PathVariable Long listingId,
            @PathVariable Long otherUserId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(messageUseCase.getConversation(listingId, otherUserId, page, size));
    }
}
