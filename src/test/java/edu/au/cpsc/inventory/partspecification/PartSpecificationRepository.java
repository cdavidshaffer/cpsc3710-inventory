package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

public class PartSpecificationRepository {

  private List<PartSpecification> partSpecifications;

  public PartSpecificationRepository() {
    partSpecifications = new ArrayList<>();
  }

  public void save(PartSpecification partSpecification) {
    partSpecifications.add(partSpecification);
  }

  public List<PartSpecification> findAll() {
    return partSpecifications;
  }
}
