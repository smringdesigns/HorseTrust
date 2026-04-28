-- ===================================================
-- V1: Tabla de usuarios
-- ===================================================
CREATE TABLE IF NOT EXISTS users (
    id                  BIGINT          NOT NULL AUTO_INCREMENT,
    username            VARCHAR(50)     NOT NULL,
    email               VARCHAR(255)    NOT NULL,
    password            VARCHAR(255)    NOT NULL,
    first_name          VARCHAR(100),
    last_name           VARCHAR(100),
    phone               VARCHAR(20),
    location            VARCHAR(200),
    profile_image_url   VARCHAR(500),
    verified            BOOLEAN         NOT NULL DEFAULT FALSE,
    active              BOOLEAN         NOT NULL DEFAULT TRUE,
    verification_token  VARCHAR(255),
    average_rating      DECIMAL(3,2),
    total_sales         INT             DEFAULT 0,
    created_at          DATETIME        NOT NULL,
    updated_at          DATETIME,
    created_by          VARCHAR(100),
    updated_by          VARCHAR(100),
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_email UNIQUE (email),
    CONSTRAINT uq_users_username UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    token      VARCHAR(500) NOT NULL,
    expires_at DATETIME     NOT NULL,
    revoked    BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id),
    CONSTRAINT uq_refresh_tokens_token UNIQUE (token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
