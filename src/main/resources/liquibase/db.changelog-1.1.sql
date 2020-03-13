-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id serial,
    name VARCHAR (255) NOT NULL,
    address VARCHAR (255) NOT NULL,
    birthday TIMESTAMP default null,
    age BIGINT default null ,
    PRIMARY KEY (id)
)
