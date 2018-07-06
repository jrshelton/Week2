DROP TABLE IF EXISTS stock;

CREATE TABLE stock
(
    id varchar(36) NOT NULL,
    symbol varchar(200) NOT NULL,
    price double DEFAULT NULL,
    volume int NOT NULL,
    date date ,
    PRIMARY KEY (id)
);


