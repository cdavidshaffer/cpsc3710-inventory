package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.databaseaccess.Session;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabasePartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.sql.DatabaseSupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Start the console user interface.  This class currently serves as documentation about the
 * start-up of the system.  Especially in terms of injecting dependencies.
 */
public class Main {

  /**
   * Start the console user interface.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws SQLException {
    Session session = new Session(
        DriverManager.getConnection("jdbc:derby://localhost:1528/inventory"));
    DatabaseSupplierRepository supplierRepository = new DatabaseSupplierRepository(session);
    new CreatePartSpecificationConsoleUserInterface(
        new CreatePartSpecification(
            new DatabasePartSpecificationRepository(session, supplierRepository),
            supplierRepository)).run();
  }

}
