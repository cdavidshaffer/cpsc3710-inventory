CREATE TABLE PartSpecificationsToSuppliers
(
    partSpecificationId BIGINT NOT NULL,
    supplierId          BIGINT NOT NULL,
    FOREIGN KEY (partSpecificationId) REFERENCES PartSpecifications (id),
    FOREIGN KEY (supplierId) REFERENCES Suppliers (id)
);
