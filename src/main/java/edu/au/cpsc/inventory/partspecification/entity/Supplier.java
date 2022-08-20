package edu.au.cpsc.inventory.partspecification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

/**
 * A company that sells parts.
 */
@jakarta.persistence.Entity
@Table(name = "Suppliers")
public class Supplier extends Entity {

  @Column(length = 1024)
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
