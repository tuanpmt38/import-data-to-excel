-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS category;

CREATE TABLE category(
    id serial,
    name VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
)
