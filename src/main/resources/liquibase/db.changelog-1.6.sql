-- liquibase formatted sql
-- changeset tuanpm:1.1

ALTER TABLE product DROP COLUMN color_id;
ALTER TABLE product Add COLUMN color varchar (255);
