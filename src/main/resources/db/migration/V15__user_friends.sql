CREATE TABLE IF NOT EXISTS friends(
    user_id bigint not null references users,
    friend_id bigint not null references users
)
