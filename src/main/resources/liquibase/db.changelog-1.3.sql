-- liquibase formatted sql
-- changeset tuanpm:1.1

ALTER TABLE product ALTER COLUMN price SET DATA TYPE INT;
