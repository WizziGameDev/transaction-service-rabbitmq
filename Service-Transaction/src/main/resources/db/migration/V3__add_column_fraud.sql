ALTER TABLE transactions
    ADD COLUMN channel VARCHAR(255),
    ADD COLUMN user_ip VARCHAR(255),
    ADD COLUMN device_name VARCHAR(255),
    ADD COLUMN location VARCHAR(255);
