create table app_users(
    id uuid primary key,
    display_name varchar(80) not null,
    email varchar(254) not null,
    password_hash varchar(255) not null,
    created_at timestamptz not null,
    updated_at timestamptz not null
);

create unique index ux_app_users_email on app_users(
    lower(email)
);