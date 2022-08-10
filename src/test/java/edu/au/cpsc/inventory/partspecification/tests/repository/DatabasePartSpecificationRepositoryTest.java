package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.dataaccess.Session;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabasePartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.tests.utils.SQLUtilities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;

public class DatabasePartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  private Session session;

  @BeforeEach
  public void createTestDatabaseTables() throws SQLException, IOException {
    try (Connection c = DriverManager.getConnection("jdbc:derby://localhost:1528/inventory")) {
      SQLUtilities.executeSqlFile("/sql/create_part_specifications.sql", c);
      c.commit();
    }
  }

  protected PartSpecificationRepository createRepository() throws SQLException {
    session = new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory"));
    return new DatabasePartSpecificationRepository(session);
  }
}
