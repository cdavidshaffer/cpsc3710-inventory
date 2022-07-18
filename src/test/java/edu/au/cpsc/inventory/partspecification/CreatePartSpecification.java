package edu.au.cpsc.inventory.partspecification;

import java.util.List;

public class CreatePartSpecification {

  private PartSpecificationRepository repository;

  public CreatePartSpecification(PartSpecificationRepository repository) {
    this.repository = repository;
  }

  public List<PartSpecification> getPartSpecifications() {
    return repository.findAll();
  }

  public void save(PartSpecification partSpecification) {
    repository.save(partSpecification);
  }
}
