package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

  private List<Supplier> suppliers;
  private long lastId;

  public SupplierRepository() {
    suppliers = new ArrayList<>();
    lastId = 0;
  }

  private Long nextId() {
    return Long.valueOf(lastId++);
  }

  private void ensureId(Supplier supplier) {
    if (supplier.getId() != null) {
      return;
    }
    supplier.setId(nextId());
  }

  public void save(Supplier supplier) {
    ensureId(supplier);
    suppliers.add(supplier);
  }

  public List<Supplier> findAll() {
    return suppliers;
  }

  public Supplier findOne(Long supplierId) {
    for (var s : suppliers) {
      if (s.getId().equals(supplierId)) {
        return s;
      }
    }
    return null;
  }
}
