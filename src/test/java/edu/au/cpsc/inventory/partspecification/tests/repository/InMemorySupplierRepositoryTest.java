package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;

public class InMemorySupplierRepositoryTest extends SupplierRepositoryTest {

  protected SupplierRepository createRepository() {
    return new InMemorySupplierRepository();
  }
}
