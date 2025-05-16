CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created_at BIGINT,
    updated_at BIGINT,
    deleted BOOLEAN
);
