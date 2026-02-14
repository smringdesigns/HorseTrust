-- ===================================================
-- V4: Videos de rendimiento
-- ===================================================
CREATE TABLE IF NOT EXISTS performance_videos (
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    listing_id       BIGINT       NOT NULL,
    title            VARCHAR(200),
    description      TEXT,
    video_url        VARCHAR(500) NOT NULL,
    thumbnail_url    VARCHAR(500),
    status           VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
    rejection_reason TEXT,
    uploaded_by      BIGINT       NOT NULL,
    reviewed_by      BIGINT,
    reviewed_at      DATETIME,
    created_at       DATETIME     NOT NULL,
    updated_at       DATETIME,
    created_by       VARCHAR(100),
    updated_by       VARCHAR(100),
    CONSTRAINT pk_performance_videos PRIMARY KEY (id),
    CONSTRAINT fk_videos_listing FOREIGN KEY (listing_id) REFERENCES horse_listings(id) ON DELETE CASCADE,
    CONSTRAINT chk_video_status CHECK (status IN ('PENDING','APPROVED','REJECTED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_videos_listing_id ON performance_videos(listing_id);
CREATE INDEX idx_videos_status     ON performance_videos(status);
