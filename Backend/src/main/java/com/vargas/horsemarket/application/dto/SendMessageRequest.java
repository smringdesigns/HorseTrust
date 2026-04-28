package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SendMessageRequest {
    @NotNull
    private Long listingId;
    @NotNull
    private Long receiverId;
    @NotBlank
    private String content;
}
