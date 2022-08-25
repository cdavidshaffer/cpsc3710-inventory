package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class InMemoryPartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  protected PartSpecificationRepository createRepository() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    return new InMemoryPartSpecificationRepository(validatorFactory.getValidator());
  }
}
