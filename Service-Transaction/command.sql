CREATE DATABASE fraud_detection;

SELECT * FROM transactions;

SELECT * FROM users;

CREATE DATABASE transaction_detection;

DELETE FROM transactions;

INSERT INTO users (name, email, password, created_at, updated_at, deleted)
VALUES
    ('Budi Santoso', 'budi@example.com', 'hashed_password1', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Siti Aminah', 'siti@example.com', 'hashed_password2', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('John Doe', 'john@example.com', 'hashed_password3', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false);


-- Ambil user_id dari users (asumsi id auto increment 1, 2, 3 sesuai urutan insert)
INSERT INTO transactions (
    user_id, transactional_code, product_name, quantity, price, total_price,
    status, created_at, updated_at, deleted, channel, user_ip, device_name, location
)
VALUES
    (1, 'TXN001', 'Laptop Lenovo', 1, 8000000, 8000000, 'SUCCESS',
     EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false,
     'MOBILE_APP', '192.168.1.10', 'Xiaomi Redmi Note 10', 'Jakarta'),

    (2, 'TXN002', 'iPhone 14', 1, 15000000, 15000000, 'SUCCESS',
     EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false,
     'WEB', '203.128.45.3', 'Macbook Air M2', 'Surabaya'),

    (3, 'TXN003', 'Smart TV Samsung', 1, 5000000, 5000000, 'FAILED',
     EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false,
     'MOBILE_WEB', '10.0.0.5', 'Samsung Galaxy S22', 'Bandung');

INSERT INTO users (name, email, password, created_at, updated_at, deleted)
VALUES
    ('Budi Santoso', 'budi@example.com', 'hashed_password1', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Siti Aminah', 'siti@example.com', 'hashed_password2', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('John Doe', 'john@example.com', 'hashed_password3', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),

    ('Agus Salim', 'agus.salim@example.com', 'hashed_password4', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Dewi Lestari', 'dewi.lestari@example.com', 'hashed_password5', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Fajar Pratama', 'fajar.pratama@example.com', 'hashed_password6', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Gita Ramadhani', 'gita.ramadhani@example.com', 'hashed_password7', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Hari Santoso', 'hari.santoso@example.com', 'hashed_password8', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Intan Permata', 'intan.permata@example.com', 'hashed_password9', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Joko Widodo', 'joko.widodo@example.com', 'hashed_password10', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),

    ('Kiki Amalia', 'kiki.amalia@example.com', 'hashed_password11', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Lina Marlina', 'lina.marlina@example.com', 'hashed_password12', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Maman Suherman', 'maman.suherman@example.com', 'hashed_password13', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Nina Kurnia', 'nina.kurnia@example.com', 'hashed_password14', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Oki Setiawan', 'oki.setiawan@example.com', 'hashed_password15', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Putri Ayu', 'putri.ayu@example.com', 'hashed_password16', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Rizky Hidayat', 'rizky.hidayat@example.com', 'hashed_password17', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Sari Dewi', 'sari.dewi@example.com', 'hashed_password18', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Tono Suwanto', 'tono.suwanto@example.com', 'hashed_password19', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Umi Salamah', 'umi.salamah@example.com', 'hashed_password20', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Vina Melinda', 'vina.melinda@example.com', 'hashed_password21', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Wawan Kurniawan', 'wawan.kurniawan@example.com', 'hashed_password22', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false),
    ('Yuni Astuti', 'yuni.astuti@example.com', 'hashed_password23', EXTRACT(EPOCH FROM NOW()) * 1000, EXTRACT(EPOCH FROM NOW()) * 1000, false);

UPDATE users SET
                 name = 'Riyan Hamdan',
                 email = 'riyan.hamdan@example.com',
                 password = 'hashed_password4',
                 created_at = 1747388966405,
                 updated_at = 1747388966405
WHERE id = 4;

UPDATE users SET
                 name = 'Siti Hanaf',
                 email = 'siti.hanaf@example.com',
                 password = 'hashed_password5',
                 created_at = 1747388966406,
                 updated_at = 1747388966406
WHERE id = 5;

UPDATE users SET
                 name = 'Bery Clus',
                 email = 'bery.clus@example.com',
                 password = 'hashed_password6',
                 created_at = 1747388966407,
                 updated_at = 1747388966407
WHERE id = 6;
