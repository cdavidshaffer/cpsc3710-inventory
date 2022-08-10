package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabasePartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.tests.utils.SQLUtilities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;

public class SQLCreatePartSpecificationTest extends CreatePartSpecificationTest {


  @BeforeEach
  public void createTestDatabaseTables() throws SQLException, IOException {
    try (Connection c = DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")) {
      SQLUtilities.executeSqlFile("/sql/create_part_specifications.sql", c);
      c.commit();
    }
  }

  protected void createRepositories() throws SQLException {
    partSpecificationRepository = new DatabasePartSpecificationRepository(new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")));
    supplierRepository = new InMemorySupplierRepository();
  }
}
