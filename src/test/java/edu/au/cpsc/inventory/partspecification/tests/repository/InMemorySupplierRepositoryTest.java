package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import java.sql.SQLException;

public class InMemorySupplierRepositoryTest extends SupplierRepositoryTest {

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    return new InMemorySupplierRepository();
  }
}
