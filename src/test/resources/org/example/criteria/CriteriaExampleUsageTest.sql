CREATE TABLE IF NOT EXISTS for_criteria(
  id BIGSERIAL PRIMARY KEY,
  status TEXT,
  name TEXT,
  created_at TIMESTAMPTZ
);

INSERT INTO for_criteria(status, name, created_at) VALUES
('READY', 'John', NOW() + INTERVAL '1 day'),
('DONE', 'Alex', NOW() + INTERVAL '10 day'),
('READY', 'Jurgen', NOW()),
('READY', 'Eugen', NOW() - INTERVAL '6 day'),
('DONE', 'Mark', NOW() - INTERVAL '4 day'),
('DONE', 'Oliver', NOW() - INTERVAL '2 day'),
('READY', 'Jane', NOW() - INTERVAL '1 day');