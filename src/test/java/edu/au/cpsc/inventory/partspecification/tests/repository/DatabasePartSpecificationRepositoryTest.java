package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabasePartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabaseSupplierRepository;
import edu.au.cpsc.inventory.partspecification.tests.utils.SQLUtilities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;

public class DatabasePartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {


  @BeforeEach
  public void createTestDatabaseTables() throws SQLException, IOException {
    try (Connection c = DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")) {
      SQLUtilities.executeSqlFile("/sql/005_drop_all.sql", c, true);
      SQLUtilities.executeSqlFile("/sql/010_create_part_specifications.sql", c, false);
      SQLUtilities.executeSqlFile("/sql/020_create_suppliers.sql", c, false);
      SQLUtilities.executeSqlFile("/sql/030_create_part_specifications_to_suppliers.sql", c, false);
      c.commit();
    }
  }

  protected PartSpecificationRepository createRepository() throws SQLException {
    Session session = new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory"));
    SupplierRepository supplierRepository = new DatabaseSupplierRepository(session);
    return new DatabasePartSpecificationRepository(session, supplierRepository);
  }
}
