CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE anecdoteTable
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    text text
);