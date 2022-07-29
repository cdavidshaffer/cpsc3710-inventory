DROP TABLE PartSpecifications;
CREATE TABLE PartSpecifications
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,
    name        varchar(1024),
    description varchar(1024)
);
