CREATE TABLE IF NOT EXISTS sub_type
(
    id         bigserial   not null primary key,
    created_at timestamp   not null default now(),
    name       varchar(34) not null unique,
    attack     int         not null,
    defence    int         not null,
    health     int         not null,
    icon_path     varchar   not null,
    model_path    varchar   not null
);

CREATE TABLE IF NOT EXISTS boss
(
    id            bigserial not null primary key,
    name          varchar(34),
    dropped_money int       not null default 0,
    attack        int       not null,
    defence       int       not null,
    health        int       not null,
    icon_path     varchar   not null,
    model_path    varchar   not null
)