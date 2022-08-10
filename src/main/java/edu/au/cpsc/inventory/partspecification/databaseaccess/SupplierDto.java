package edu.au.cpsc.inventory.partspecification.databaseaccess;

/**
 * Data Transport Object for rows in the Suppliers table.
 */
public class SupplierDto {

  private final Long id;
  private final String name;

  public SupplierDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
