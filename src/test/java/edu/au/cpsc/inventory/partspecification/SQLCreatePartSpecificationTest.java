package edu.au.cpsc.inventory.partspecification;

import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLCreatePartSpecificationTest extends CreatePartSpecificationTest {

  protected void createRepositories() throws SQLException {
    partSpecificationRepository = new SQLPartSpecificationRepository(new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")));
    supplierRepository = new InMemorySupplierRepository();
  }
}
