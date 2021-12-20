DROP TABLE sub_defence_skill;
DROP TABLE sub_damage_skill;

ALTER TABLE damage_skill
    ADD COLUMN IF NOT EXISTS sub_id bigint references sub;
ALTER TABLE defence_skill
    ADD COLUMN IF NOT EXISTS sub_id bigint references sub;