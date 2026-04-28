package com.vargas.horsemarket.application.dto;

import com.vargas.horsemarket.domain.enums.VerificationStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class VetRecordResponse {
    private Long id;
    private Long listingId;
    private Long vetId;
    private String vetName;
    private VerificationStatus status;
    private String generalObservations;
    private String diagnoses;
    private String vaccines;
    private LocalDate lastVaccinationDate;
    private LocalDate lastCheckupDate;
    private String healthCertificateUrl;
    private String rejectionReason;
    private LocalDateTime approvedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createdAt;
}
