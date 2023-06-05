CREATE SEQUENCE seqA START WITH 1;

CREATE TABLE anecdoteTable
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('seqA'),
    text text
);