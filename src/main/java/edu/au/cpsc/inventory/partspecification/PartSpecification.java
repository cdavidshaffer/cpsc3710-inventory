package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

/**
 * A part specification is a formal description of a component that can be purchased or
 * manufactured.  Part specifications include information about what suppliers are known to be able
 * to supply them.
 *
 * <p>Part specifications that have been persisted are assigned an id.
 */
public class PartSpecification {

  private List<Supplier> suppliers;
  private Long id;
  private String name;
  private String description;

  public PartSpecification() {
    suppliers = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Add a supplier to the list of suppliers that manufacture or supply this part.
   *
   * @param supplier the supplier to add
   */
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
