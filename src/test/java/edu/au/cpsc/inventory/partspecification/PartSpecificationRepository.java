package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

public class PartSpecificationRepository {

  private List<PartSpecification> partSpecifications;
  private long lastId;

  public PartSpecificationRepository() {
    partSpecifications = new ArrayList<>();
    lastId = 0;
  }

  private Long nextId() {
    return Long.valueOf(lastId++);
  }

  private void ensureId(PartSpecification partSpecification) {
    if (partSpecification.getId() != null) {
      return;
    }
    partSpecification.setId(nextId());
  }

  public void save(PartSpecification partSpecification) {
    ensureId(partSpecification);
    partSpecifications.add(partSpecification);
  }

  public List<PartSpecification> findAll() {
    return partSpecifications;
  }
}
