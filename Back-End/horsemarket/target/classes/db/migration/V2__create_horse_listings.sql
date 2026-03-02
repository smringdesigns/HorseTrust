-- ===================================================
-- V2: Tabla de anuncios de caballos
-- ===================================================
CREATE TABLE IF NOT EXISTS horse_listings (
    id            BIGINT          NOT NULL AUTO_INCREMENT,
    title         VARCHAR(200)    NOT NULL,
    description   TEXT,
    horse_name    VARCHAR(100),
    breed         VARCHAR(100),
    age_years     INT,
    sex           VARCHAR(20),
    discipline    VARCHAR(100),
    level         VARCHAR(50),
    price         DECIMAL(12,2)   NOT NULL,
    currency_code VARCHAR(10)     NOT NULL DEFAULT 'MXN',
    location      VARCHAR(200),
    status        VARCHAR(30)     NOT NULL DEFAULT 'DRAFT',
    premium       BOOLEAN         NOT NULL DEFAULT FALSE,
    seller_id     BIGINT          NOT NULL,
    published_at  DATETIME,
    sold_at       DATETIME,
    created_at    DATETIME        NOT NULL,
    updated_at    DATETIME,
    created_by    VARCHAR(100),
    updated_by    VARCHAR(100),
    CONSTRAINT pk_horse_listings PRIMARY KEY (id),
    CONSTRAINT fk_horse_listings_seller FOREIGN KEY (seller_id) REFERENCES users(id),
    CONSTRAINT chk_listing_status CHECK (status IN ('DRAFT','PUBLISHED','UNDER_VERIFICATION','VERIFIED','REJECTED','SOLD','SUSPENDED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS listing_images (
    listing_id BIGINT       NOT NULL,
    image_url  VARCHAR(500) NOT NULL,
    CONSTRAINT fk_listing_images FOREIGN KEY (listing_id) REFERENCES horse_listings(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_horse_listings_status  ON horse_listings(status);
CREATE INDEX idx_horse_listings_seller  ON horse_listings(seller_id);
CREATE INDEX idx_horse_listings_breed   ON horse_listings(breed);
CREATE INDEX idx_horse_listings_premium ON horse_listings(premium);
