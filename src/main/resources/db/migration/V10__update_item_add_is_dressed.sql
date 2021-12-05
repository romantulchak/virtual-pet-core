ALTER TABLE IF EXISTS armor
    ADD COLUMN has_dressed bool default false;
ALTER TABLE IF EXISTS sword
    ADD COLUMN has_dressed bool default false;