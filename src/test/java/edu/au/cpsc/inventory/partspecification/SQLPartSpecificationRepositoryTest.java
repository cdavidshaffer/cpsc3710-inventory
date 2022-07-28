package edu.au.cpsc.inventory.partspecification;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;

public class SQLPartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  private Session session;

  protected PartSpecificationRepository createRepository() throws SQLException {
    session = new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory"));
    return new SQLPartSpecificationRepository(session);
  }

  @AfterEach
  public void tearDown() throws SQLException {
    session.rollback();
  }
}
