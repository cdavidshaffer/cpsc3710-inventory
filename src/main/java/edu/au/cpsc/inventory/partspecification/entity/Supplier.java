package edu.au.cpsc.inventory.partspecification.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * A company that sells parts.
 */
@javax.persistence.Entity
@Table(name = "Suppliers")
public class Supplier extends Entity {

  @Column(length = 1024)
  @NotBlank
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
