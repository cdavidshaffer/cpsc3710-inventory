package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
import java.util.List;

/**
 * I provide access to a collection of part specifications.  New specifications can be added, and
 * they will be assigned unique ids.
 */
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

  /**
   * Store the specified part specification to this repository.  If the specification does not have
   * an Id, one will be assigned.
   *
   * @param partSpecification the part specification to add
   */
  public void save(PartSpecification partSpecification) {
    ensureId(partSpecification);
    partSpecifications.add(partSpecification);
  }

  /**
   * Return a list of all part specifications that have been saved in this repository.
   *
   * @return all part specifications that have been saved in this repository
   */
  public List<PartSpecification> findAll() {
    return partSpecifications;
  }
}
