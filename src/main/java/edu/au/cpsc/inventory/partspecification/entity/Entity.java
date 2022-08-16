package edu.au.cpsc.inventory.partspecification.entity;

/**
 * My concrete instances each have an id that is unique by class.
 */
public abstract class Entity {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
