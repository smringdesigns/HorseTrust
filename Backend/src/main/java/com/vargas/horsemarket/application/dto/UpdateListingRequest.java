package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdateListingRequest {
    @Size(max = 200)
    private String title;
    @Size(max = 5000)
    private String description;
    private String horseName;
    private String breed;
    @Min(0) @Max(50)
    private Integer ageYears;
    private String sex;
    private String discipline;
    private String level;
    @DecimalMin("0.01")
    private BigDecimal price;
    private String currencyCode;
    private String location;
}
