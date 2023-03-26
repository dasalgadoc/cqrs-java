USE cqrs;
create table counter (
    entity varchar(100),
    entries integer,
    PRIMARY KEY (entity)
);
