package edu.au.cpsc.inventory.partspecification;

public class DefaultSupplierModel implements SupplierModel {

  private Long id;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
