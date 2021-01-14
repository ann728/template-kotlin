create table index
(
    id   serial  not null
        constraint index_pk
            primary key,
    name varchar not null
);

create table "user"
(
    login_user varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    role_cd char(2) not null
);

create unique index user_login_user_uindex on "user" (login_user);
alter table "user" add constraint user_pk primary key (login_user);




