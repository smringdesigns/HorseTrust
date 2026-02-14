package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VetVerificationRequest {
    @NotNull
    private Boolean approved;
    private String rejectionReason;
}
