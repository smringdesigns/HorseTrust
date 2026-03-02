-- ===================================================
-- V6: Calificaciones y reputación
-- ===================================================
CREATE TABLE IF NOT EXISTS ratings (
    id         BIGINT   NOT NULL AUTO_INCREMENT,
    listing_id BIGINT   NOT NULL,
    buyer_id   BIGINT   NOT NULL,
    seller_id  BIGINT   NOT NULL,
    score      INT      NOT NULL CHECK (score BETWEEN 1 AND 5),
    comment    TEXT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    CONSTRAINT pk_ratings PRIMARY KEY (id),
    CONSTRAINT uq_rating_listing_buyer UNIQUE (listing_id, buyer_id),
    CONSTRAINT fk_ratings_listing FOREIGN KEY (listing_id) REFERENCES horse_listings(id),
    CONSTRAINT fk_ratings_buyer   FOREIGN KEY (buyer_id)   REFERENCES users(id),
    CONSTRAINT fk_ratings_seller  FOREIGN KEY (seller_id)  REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_ratings_seller_id ON ratings(seller_id);
