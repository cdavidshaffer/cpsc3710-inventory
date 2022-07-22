package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

/**
 * I provide access to a collection of suppliers.  New suppliers can be added, and they will be
 * assigned unique ids.
 */
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

  /**
   * Store the specified supplier to this repository.  If the supplier does not have an id, one will
   * be assigned.
   *
   * @param supplier the supplier to add
   * @return the id of the object that was saved
   */
  public Long save(Supplier supplier) {
    ensureId(supplier);
    suppliers.add(supplier);
    return supplier.getId();
  }

  public List<Supplier> findAll() {
    return suppliers;
  }

  /**
   * Given an id, return the supplier with that id or null if that supplier does not exist.
   *
   * @param supplierId the id of the supplier to find
   * @return the supplier with that id or null if there is none
   */
  public Supplier findOne(Long supplierId) {
    for (var s : suppliers) {
      if (s.getId().equals(supplierId)) {
        return s;
      }
    }
    return null;
  }
}
