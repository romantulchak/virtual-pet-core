CREATE TABLE IF NOT EXISTS inventory
(
    id       bigserial not null primary key,
    max_size int       not null
);


CREATE TABLE IF NOT EXISTS level
(
    id    bigserial not null primary key,
    level bigint    not null
);

CREATE TABLE IF NOT EXISTS dressed_items
(
    id           bigserial not null primary key,
    sword_id     bigint references sword,
    legs_id      bigint references armor,
    shoulders_id bigint references armor,
    body_id      bigint references armor,
    hands_id     bigint references armor,
    head_id      bigint references armor,
    shield_id    bigint references armor
);

CREATE TABLE IF NOT EXISTS sub
(
    id                bigserial   not null primary key,
    name              varchar(40) not null unique,
    inventory_id      bigint      not null references inventory,
    user_id           bigint      not null references users,
    money_up_level    int         not null,
    money_up_price    int         not null,
    money_multiplier  int         not null default 1,
    level_id          bigint      not null references level,
    attack            int         not null,
    defence           int         not null,
    health            int         not null,
    attack_up_level   int         not null,
    attack_multiplier int         not null,
    attack_money_up   int         not null,
    money             bigint      not null default 0,
    diamond           bigint      not null default 0,
    emerald           bigint      not null default 0,
    max_diamond       int         not null,
    icon_path         varchar     not null,
    model_path        varchar     not null,
    dressed_items_id  bigint references dressed_items,
    sub_type_id       bigint      not null references sub_type
);


