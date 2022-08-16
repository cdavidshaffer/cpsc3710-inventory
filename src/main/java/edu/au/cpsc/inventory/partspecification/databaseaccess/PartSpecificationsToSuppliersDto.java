package edu.au.cpsc.inventory.partspecification.databaseaccess;

/**
 * Data Transfer Object for the PartSpecificationsToSuppliers table.  My instance variables reflect
 * columns in that table.  I am used by a DAO to fetch and store rows in this table.
 */
public class PartSpecificationsToSuppliersDto {

  private Long partSpecificationId;
  private Long supplierId;

  public PartSpecificationsToSuppliersDto(Long partSpecificationId, Long supplierId) {
    this.partSpecificationId = partSpecificationId;
    this.supplierId = supplierId;
  }

  public Long getPartSpecificationId() {
    return partSpecificationId;
  }

  public void setPartSpecificationId(Long partSpecificationId) {
    this.partSpecificationId = partSpecificationId;
  }

  public Long getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }
}
