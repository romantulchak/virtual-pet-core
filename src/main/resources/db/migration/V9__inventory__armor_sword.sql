ALTER TABLE armor
    ADD COLUMN IF NOT EXISTS inventory_id bigint references inventory;

ALTER TABLE sword
    ADD COLUMN IF NOT EXISTS inventory_id bigint references inventory;