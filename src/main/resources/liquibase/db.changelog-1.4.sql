-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS color;

CREATE TABLE color(
    id serial,
    name VARCHAR (255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE product DROP COLUMN color;

CREATE TABLE product_color(
    id serial,
    product_id bigint not null,
    color_id bigint not null,
    PRIMARY KEY (id)
);
