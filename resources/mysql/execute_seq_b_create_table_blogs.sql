USE cqrs;
create table blogs (
    id varchar(30),
    title varchar(150),
    type varchar(100),
    brief varchar(500),
    url varchar(255),
    PRIMARY KEY (id)
);
