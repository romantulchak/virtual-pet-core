CREATE TABLE IF NOT EXISTS sub_damage_skill
(
    sub_id bigint not null references sub,
    damage_skill_id bigint not null references damage_skill
);

CREATE TABLE IF NOT EXISTS sub_defence_skill
(
    sub_id bigint not null references sub,
    defence_skill_id bigint not null references defence_skill
);


