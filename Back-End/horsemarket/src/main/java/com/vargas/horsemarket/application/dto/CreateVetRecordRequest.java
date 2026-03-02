package com.vargas.horsemarket.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateVetRecordRequest {

    @NotNull(message = "El ID del anuncio es requerido")
    private Long listingId;

    private String generalObservations;
    private String diagnoses;
    private String vaccines;
    private LocalDate lastVaccinationDate;
    private LocalDate lastCheckupDate;
    private String healthCertificateUrl;
}
