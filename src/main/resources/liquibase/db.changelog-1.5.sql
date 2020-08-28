-- liquibase formatted sql
-- changeset tuanpm:1.1

ALTER TABLE product ADD COLUMN color_id BIGINT default null;
