package edu.au.cpsc.inventory.partspecification.entity;

/**
 * A company that sells parts.
 */
public class Supplier extends Entity {

  private String name;

  public Supplier() {
  }

  public Supplier(Long id, String name) {
    setId(id);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
