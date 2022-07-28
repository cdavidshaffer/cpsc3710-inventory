package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;

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
  public static void main(String[] args) {
    new CreatePartSpecificationConsoleUserInterface(
        new CreatePartSpecification(new InMemoryPartSpecificationRepository(),
            new InMemorySupplierRepository())).run();
  }

}
