-- ===================================================
-- V5: Mensajería interna
-- ===================================================
CREATE TABLE IF NOT EXISTS messages (
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    listing_id  BIGINT   NOT NULL,
    sender_id   BIGINT   NOT NULL,
    receiver_id BIGINT   NOT NULL,
    content     TEXT     NOT NULL,
    is_read     BOOLEAN  NOT NULL DEFAULT FALSE,
    read_at     DATETIME,
    created_at  DATETIME NOT NULL,
    updated_at  DATETIME,
    created_by  VARCHAR(100),
    updated_by  VARCHAR(100),
    CONSTRAINT pk_messages PRIMARY KEY (id),
    CONSTRAINT fk_messages_listing  FOREIGN KEY (listing_id)  REFERENCES horse_listings(id),
    CONSTRAINT fk_messages_sender   FOREIGN KEY (sender_id)   REFERENCES users(id),
    CONSTRAINT fk_messages_receiver FOREIGN KEY (receiver_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_messages_listing_id  ON messages(listing_id);
CREATE INDEX idx_messages_receiver_id ON messages(receiver_id);
CREATE INDEX idx_messages_unread      ON messages(receiver_id, is_read);
