package edu.au.cpsc.inventory.partspecification.repository.inmemory;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import javax.validation.Validator;

/**
 * I provide access to a collection of part specifications.  New specifications can be added, and
 * they will be assigned unique ids.
 */
public class InMemoryPartSpecificationRepository extends
    InMemoryEntityRepository<PartSpecification> implements PartSpecificationRepository {


  public InMemoryPartSpecificationRepository(Validator validator) {
    super(validator);
  }
}
