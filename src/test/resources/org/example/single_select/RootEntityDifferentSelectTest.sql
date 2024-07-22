CREATE TABLE single_select_root_entity(
  id BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ
);

CREATE TABLE single_select_referenced_entity(
  id BIGSERIAL PRIMARY KEY,
  status TEXT,
  root_entity_id BIGINT
);