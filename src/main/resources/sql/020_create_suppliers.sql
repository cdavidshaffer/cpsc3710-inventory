CREATE TABLE Suppliers
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY,
    name varchar(1024),
    PRIMARY KEY (id)
);
