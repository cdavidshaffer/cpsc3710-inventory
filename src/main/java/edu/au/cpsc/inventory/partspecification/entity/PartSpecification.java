package edu.au.cpsc.inventory.partspecification.entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A part specification is a formal description of a component that can be purchased or
 * manufactured.  Part specifications include information about what suppliers are known to be able
 * to supply them.
 *
 * <p>Part specifications that have been persisted are assigned an id.
 */
@jakarta.persistence.Entity
@Table(name = "PartSpecifications")
public class PartSpecification extends Entity {

  private String name;
  private String description;
  @ManyToMany
  private List<Supplier> suppliers;

  /**
   * Construct a PartSpecification by including all state variables.  Normally used to reify a
   * PartSpecification from an on-disk version.
   *
   * @param id          (optional) id of the object
   * @param name        (required) name of the part specification
   * @param description (required) description of the part specification
   * @param suppliers   (required) list of suppliers associated with this part specification
   */
  public PartSpecification(Long id, String name, String description, List<Supplier> suppliers) {
    this();
    setId(id);
    this.name = name;
    this.description = description;
    this.suppliers = suppliers;
  }

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


}
