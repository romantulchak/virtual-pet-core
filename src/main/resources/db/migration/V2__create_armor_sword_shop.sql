CREATE TABLE IF NOT EXISTS shop
(
    id bigserial not null primary key
);

CREATE TABLE IF NOT EXISTS armor
(
    id            bigserial   not null primary key,
    armor         int         not null,
    health        int         not null,
    shop_id       bigint references shop,
    uniqueness    varchar     not null,
    icon_path     varchar     not null,
    name          varchar(60) not null,
    item_type     varchar     not null,
    item_category varchar     not null,
    price         int         not null
);

CREATE TABLE IF NOT EXISTS sword
(
    id                bigserial   not null primary key,
    attack            int         not null,
    is_shield_allowed bool        not null,
    shop_id           bigint references shop,
    uniqueness        varchar     not null,
    icon_path         varchar     not null,
    name              varchar(60) not null,
    item_type         varchar     not null,
    item_category     varchar     not null,
    price             int         not null
);

