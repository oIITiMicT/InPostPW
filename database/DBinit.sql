create table permissions
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table permissions
    owner to admin;

create table roles
(
    id   bigint not null
        primary key,
    name varchar(255)
);

alter table roles
    owner to admin;

create table roles_permissions
(
    role_id       bigint not null
        constraint fkqi9odri6c1o81vjox54eedwyh
            references roles,
    permission_id bigint not null
        constraint fkbx9r9uw77p58gsq4mus0mec0o
            references permissions,
    primary key (role_id, permission_id)
);

alter table roles_permissions
    owner to admin;

create table users
(
    id            bigserial
        primary key,
    email         varchar(255),
    password_hash varchar(255),
    username      varchar(255),
    role_id       bigint
        constraint fkp56c1712k691lhsyewcssf40f
            references roles
);

alter table users
    owner to admin;

create table packages
(
    id           bigint not null
        primary key,
    tracker      varchar(255),
    recipient_id bigint
        constraint fkq827gv8cke31ayuiua176h3b0
            references users,
    sender_id    bigint
        constraint fkihk0xeiy1mfjc4m56uofkg2nr
            references users
);

alter table packages
    owner to admin;

create table stages
(
    id          bigint not null
        primary key,
    description varchar(255),
    time        timestamp,
    package_id  bigint
        constraint fknpvcluu8fd9515cu0w66twtur
            references packages
);

alter table stages
    owner to admin;

