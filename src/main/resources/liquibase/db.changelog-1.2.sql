-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS customer;

CREATE TABLE product(
    id serial,
    title VARCHAR (255) NOT NULL,
    price DECIMAL,
    size INT,
    description VARCHAR (255),
    image VARCHAR (255) ,
    color VARCHAR (255) ,
    PRIMARY KEY (id)
)
