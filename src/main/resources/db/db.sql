-- create database
create database kotlin;

-- create table
create table index
(
    id   serial  not null
        constraint index_pk
            primary key,
    name varchar not null
);

create table "users"
(
    login_user varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    role_cd char(2) not null
);

create unique index users_login_user_uindex on "users" (login_user);
alter table "users" add constraint users_pk primary key (login_user);

-- insert
insert into index (name) values('aaa');

insert into users values ('user', '$2a$10$FkZmBHeMnIDLy3EXZbfGUevdoRMj2wlx6GMObvoDO.41eh3TMJsdq', 'user', '01');
insert into users values ('admin', '$2a$10$CKdmcxkPlJWqzKqWYNpiJOkGFBO3dDwBMYdYvo9atvaSRLlHDUVse', 'admin', '00');
