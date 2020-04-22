-- liquibase formatted sql
-- changeset tuanpm:1.1

DROP TABLE IF EXISTS student;

CREATE TABLE student(
    id serial,
    name VARCHAR (255)  NULL,
    address VARCHAR (255)  NULL,
    age BIGINT default null ,
    created_by int default null,
    updated_by int ,
    created_at TIMESTAMP default null,
    updated_at TIMESTAMP default null,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS student_history;

CREATE TABLE student_history(
    id serial,
    name VARCHAR (255)  NULL,
    created_by int default null,
    student_id bigint     null default 0,
    updated_by int ,
    created_at TIMESTAMP default null,
    updated_at TIMESTAMP default null,
    CONSTRAINT student_history_fk1 FOREIGN KEY (student_id) REFERENCES student (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
)
