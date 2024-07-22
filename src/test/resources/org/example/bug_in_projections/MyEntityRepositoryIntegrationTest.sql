CREATE TABLE IF NOT EXISTS my_entity(
    id BIGSERIAL PRIMARY KEY,
    status TEXT,
    created_at TIMESTAMPTZ
);