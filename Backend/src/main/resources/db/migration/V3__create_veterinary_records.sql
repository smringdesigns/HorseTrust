-- ===================================================
-- V3: Registros veterinarios
-- ===================================================
CREATE TABLE IF NOT EXISTS veterinary_records (
    id                      BIGINT  NOT NULL AUTO_INCREMENT,
    listing_id              BIGINT  NOT NULL,
    vet_id                  BIGINT  NOT NULL,
    status                  VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    general_observations    TEXT,
    diagnoses               TEXT,
    vaccines                TEXT,
    last_vaccination_date   DATE,
    last_checkup_date       DATE,
    health_certificate_url  VARCHAR(500),
    rejection_reason        TEXT,
    approved_at             DATETIME,
    rejected_at             DATETIME,
    created_at              DATETIME    NOT NULL,
    updated_at              DATETIME,
    created_by              VARCHAR(100),
    updated_by              VARCHAR(100),
    CONSTRAINT pk_veterinary_records PRIMARY KEY (id),
    CONSTRAINT uq_vet_records_listing UNIQUE (listing_id),
    CONSTRAINT fk_vet_records_listing FOREIGN KEY (listing_id) REFERENCES horse_listings(id) ON DELETE CASCADE,
    CONSTRAINT fk_vet_records_vet     FOREIGN KEY (vet_id) REFERENCES users(id),
    CONSTRAINT chk_vet_status CHECK (status IN ('PENDING','IN_PROGRESS','APPROVED','REJECTED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_vet_records_vet_id ON veterinary_records(vet_id);
CREATE INDEX idx_vet_records_status ON veterinary_records(status);
