package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.sql.SQLException;

public class InMemorySupplierRepositoryTest extends SupplierRepositoryTest {

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    return new InMemorySupplierRepository(validatorFactory.getValidator());
  }
}
