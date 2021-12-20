CREATE TABLE IF NOT EXISTS damage_skill
(
    id                bigserial    not null primary key,
    name              varchar(46)  not null unique,
    skill_category    varchar      not null,
    price             int          not null,
    skill_description varchar(300) not null,
    cooldown          timestamp,
    max_cooldown      int          not null,
    icon              varchar      not null,
    damage            int          not null,
    critical_chance   float8       not null,
    shop_id           bigint references shop
);

CREATE TABLE IF NOT EXISTS defence_skill
(
    id                bigserial    not null primary key,
    name              varchar(46)  not null unique,
    skill_category    varchar      not null,
    price             int          not null,
    skill_description varchar(300) not null,
    cooldown          timestamp,
    max_cooldown      int          not null,
    icon              varchar      not null,
    health            int          not null,
    defence           float8       not null,
    time_of_action    int          not null,
    shop_id           bigint references shop
);