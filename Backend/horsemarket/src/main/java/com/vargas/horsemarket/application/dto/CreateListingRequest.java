package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateListingRequest {

    @NotBlank(message = "El título es requerido")
    @Size(max = 200)
    private String title;

    @Size(max = 5000)
    private String description;

    @NotBlank(message = "El nombre del caballo es requerido")
    private String horseName;

    @NotBlank(message = "La raza es requerida")
    private String breed;

    @Min(value = 0, message = "La edad debe ser positiva")
    @Max(value = 50, message = "La edad máxima es 50 años")
    private Integer ageYears;

    @NotBlank(message = "El sexo es requerido")
    private String sex;

    private String discipline;
    private String level;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal price;

    @NotBlank(message = "La moneda es requerida")
    private String currencyCode;

    private String location;
}
