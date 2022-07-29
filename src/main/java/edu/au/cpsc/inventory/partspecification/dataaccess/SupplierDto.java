package edu.au.cpsc.inventory.partspecification.dataaccess;

public class SupplierDto {

  private Long id;
  private String name;

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
