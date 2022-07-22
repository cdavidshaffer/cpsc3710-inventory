package edu.au.cpsc.inventory.partspecification;

public class DefaultPartSpecificationModel implements PartSpecificationModel {

  private String name;
  private String description;
  private Long id;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
