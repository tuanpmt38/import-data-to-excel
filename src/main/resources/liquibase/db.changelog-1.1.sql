-- liquibase formatted sql
-- changeset taind:1.1

DROP TABLE IF EXISTS pm_user;

CREATE TABLE pm_user
(
    id         serial primary key  not null,
    username   varchar(255) unique not null,
    "password" varchar(255)        not null,
    salary int,
    age int
);

