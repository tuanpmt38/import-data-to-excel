-- liquibase formatted sql
-- changeset tuanpm:1.1

ALTER TABLE product DROP COLUMN color ;
ALTER TABLE product Add COLUMN color INT
