-- ===================================================
-- V8: Datos semilla para desarrollo
-- ===================================================

-- Admin default (password: Admin1234!)
INSERT INTO users (username, email, password, first_name, last_name, verified, active, created_at)
VALUES ('admin', 'admin@horsemarket.com',
        '$2a$12$H6m3RXPA7FO9gu.mRrVH6uIXpWhlmYZdJTzPZ5SBMYCLt4sJjEAqu',
        'Administrador', 'HorseMarket', TRUE, TRUE, NOW())
ON DUPLICATE KEY UPDATE id=id;

INSERT INTO user_roles (user_id, role)
SELECT id, 'ADMIN' FROM users WHERE email='admin@horsemarket.com'
ON DUPLICATE KEY UPDATE role=role;

-- Vet demo (password: Vet12345!)
INSERT INTO users (username, email, password, first_name, last_name, verified, active, created_at)
VALUES ('dr_garcia', 'vet@horsemarket.com',
        '$2a$12$H6m3RXPA7FO9gu.mRrVH6uIXpWhlmYZdJTzPZ5SBMYCLt4sJjEAqu',
        'Dr. Carlos', 'García', TRUE, TRUE, NOW())
ON DUPLICATE KEY UPDATE id=id;

INSERT INTO user_roles (user_id, role)
SELECT id, 'VET' FROM users WHERE email='vet@horsemarket.com'
ON DUPLICATE KEY UPDATE role=role;
