package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.dataaccess.Session;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.SQLSupplierRepository;
import edu.au.cpsc.inventory.partspecification.tests.utils.SQLUtilities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;

public class SQLSupplierRepositoryTest extends SupplierRepositoryTest {

  private Session session;

  @BeforeEach
  public void createTestDatabaseTables() throws SQLException, IOException {
    try (Connection c = DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")) {
      SQLUtilities.executeSqlFile("/sql/02_create_suppliers.sql", c);
      c.commit();
    }
  }

  protected SupplierRepository createRepository() throws SQLException {
    session = new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory"));
    return new SQLSupplierRepository(session);
  }
}
