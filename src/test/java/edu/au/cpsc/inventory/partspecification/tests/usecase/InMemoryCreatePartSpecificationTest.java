package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class InMemoryCreatePartSpecificationTest extends CreatePartSpecificationTest {

  protected void createRepositories() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    partSpecificationRepository = new InMemoryPartSpecificationRepository(
        validatorFactory.getValidator());
    supplierRepository = new InMemorySupplierRepository(validatorFactory.getValidator());
  }
}
