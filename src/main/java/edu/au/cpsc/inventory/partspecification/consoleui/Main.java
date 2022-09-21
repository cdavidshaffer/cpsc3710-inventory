package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.logging.LoggingSupplierRepositoryDecorator;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

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
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    SupplierRepository supplierRepository = new LoggingSupplierRepositoryDecorator(
        new JpaSupplierRepository(entityManager));
    PartSpecificationRepository partSpecificationRepository = new JpaPartSpecificationRepository(
        entityManager);
    CreatePartSpecification useCase = new CreatePartSpecification(
        partSpecificationRepository,
        supplierRepository);
    new CreatePartSpecificationConsoleUserInterface(
        useCase).run();
  }

}
