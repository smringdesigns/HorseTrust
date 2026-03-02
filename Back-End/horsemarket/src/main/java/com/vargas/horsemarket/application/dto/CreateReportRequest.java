package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateReportRequest {
    private Long reportedListingId;
    private Long reportedUserId;
    @NotBlank
    @Size(max = 200)
    private String reason;
    @Size(max = 2000)
    private String description;
}
