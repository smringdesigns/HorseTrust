-- ===================================================
-- V7: Sistema de reportes y anti-fraude
-- ===================================================
CREATE TABLE IF NOT EXISTS reports (
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    reported_by         BIGINT       NOT NULL,
    reported_listing_id BIGINT,
    reported_user_id    BIGINT,
    reason              VARCHAR(200) NOT NULL,
    description         TEXT,
    status              VARCHAR(20)  NOT NULL DEFAULT 'OPEN',
    admin_notes         TEXT,
    reviewed_by         BIGINT,
    reviewed_at         DATETIME,
    created_at          DATETIME     NOT NULL,
    updated_at          DATETIME,
    created_by          VARCHAR(100),
    updated_by          VARCHAR(100),
    CONSTRAINT pk_reports PRIMARY KEY (id),
    CONSTRAINT fk_reports_reporter   FOREIGN KEY (reported_by)         REFERENCES users(id),
    CONSTRAINT fk_reports_listing    FOREIGN KEY (reported_listing_id) REFERENCES horse_listings(id),
    CONSTRAINT fk_reports_user       FOREIGN KEY (reported_user_id)    REFERENCES users(id),
    CONSTRAINT chk_report_status CHECK (status IN ('OPEN','UNDER_REVIEW','RESOLVED','DISMISSED')),
    CONSTRAINT chk_report_target CHECK (reported_listing_id IS NOT NULL OR reported_user_id IS NOT NULL)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_reports_status ON reports(status);
CREATE INDEX idx_reports_reported_user ON reports(reported_user_id);
