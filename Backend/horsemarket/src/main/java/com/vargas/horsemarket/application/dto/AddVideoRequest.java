package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddVideoRequest {
    @NotNull
    private Long listingId;
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String videoUrl;
    private String thumbnailUrl;
}
