package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

public class PartSpecification {

  private List<Supplier> suppliers;
  private Long id;

  public PartSpecification() {
    suppliers = new ArrayList<>();
  }

  public void addSupplier(Supplier supplier) {
    suppliers.add(supplier);
  }

  public List<Supplier> getSuppliers() {
    return suppliers;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
