package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import java.sql.SQLException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class InMemorySupplierRepositoryTest extends SupplierRepositoryTest {

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    return new InMemorySupplierRepository(validatorFactory.getValidator());
  }
}
