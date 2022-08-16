package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;

public class InMemoryCreatePartSpecificationTest extends CreatePartSpecificationTest {

  protected void createRepositories() {
    partSpecificationRepository = new InMemoryPartSpecificationRepository();
    supplierRepository = new InMemorySupplierRepository();
  }
}
