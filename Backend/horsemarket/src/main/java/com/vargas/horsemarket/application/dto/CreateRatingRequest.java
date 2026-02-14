package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateRatingRequest {
    @NotNull
    private Long listingId;
    @NotNull
    @Min(1) @Max(5)
    private Integer score;
    @Size(max = 2000)
    private String comment;
}
