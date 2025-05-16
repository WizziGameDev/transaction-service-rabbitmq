CREATE TABLE transactions (
                              id SERIAL PRIMARY KEY,
                              user_id INTEGER NOT NULL,
                              transactional_code VARCHAR(255) UNIQUE NOT NULL,
                              product_name VARCHAR(255),
                              quantity INTEGER,
                              price DOUBLE PRECISION,
                              total_price DOUBLE PRECISION,
                              status VARCHAR(50),
                              created_at BIGINT,
                              updated_at BIGINT,
                              deleted BOOLEAN,
                              CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
