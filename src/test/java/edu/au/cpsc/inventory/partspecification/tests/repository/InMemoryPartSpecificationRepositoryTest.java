package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;

public class InMemoryPartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  protected PartSpecificationRepository createRepository() {
    return new InMemoryPartSpecificationRepository();
  }
}
