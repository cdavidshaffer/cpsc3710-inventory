package edu.au.cpsc.inventory.partspecification.dataaccess;

/**
 * Data Transfer Object for the PartSpecifications table.  My instance variables reflect columns in
 * that table.  I am used by a DAO to fetch and store rows in this table.
 */
public class PartSpecificationDto {

  private Long id;
  private String name;
  private String description;

  /**
   * Construct this object with all column values set.
   *
   * @param id          (optional) id of the row
   * @param name        (required) name value
   * @param description (required) description
   */
  public PartSpecificationDto(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
