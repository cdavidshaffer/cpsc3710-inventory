package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

  private List<Supplier> suppliers;

  public SupplierRepository() {
    suppliers = new ArrayList<>();
  }

  public void save(Supplier supplier) {
    suppliers.add(supplier);
  }

  public List<Supplier> findAll() {
    return suppliers;
  }
}
