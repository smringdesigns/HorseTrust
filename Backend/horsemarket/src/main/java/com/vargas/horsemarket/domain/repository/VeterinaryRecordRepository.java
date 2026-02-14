package com.vargas.horsemarket.domain.repository;

import com.vargas.horsemarket.domain.model.VeterinaryRecord;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de dominio para persistencia de registros veterinarios.
 */
public interface VeterinaryRecordRepository {

    VeterinaryRecord save(VeterinaryRecord record);

    Optional<VeterinaryRecord> findById(Long id);

    Optional<VeterinaryRecord> findByListingId(Long listingId);

    List<VeterinaryRecord> findByVetId(Long vetId);

    void deleteById(Long id);
}
