CREATE TABLE IF NOT EXISTS user_friend
(
    id              bigserial not null primary key,
    user_id         bigint    not null references users,
    user_request_id bigint    not null references users
);
