CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;

CREATE TABLE IF NOT EXISTS users
(
    id                 bigserial    not null primary key,
    username           varchar(16)  not null unique,
    email              varchar(95)  not null unique,
    password           varchar(120) not null,
    max_number_of_subs int          not null default 5,
    avatar             varchar
);

CREATE TABLE IF NOT EXISTS role
(
    id   bigserial   not null primary key,
    name varchar(35) not null
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id bigint not null references users,
    role_id bigint not null references role
);


INSERT INTO users
VALUES (3, 'Kzz', 'test@gmail.com', '1111', 5);
INSERT INTO role
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (3, 2);