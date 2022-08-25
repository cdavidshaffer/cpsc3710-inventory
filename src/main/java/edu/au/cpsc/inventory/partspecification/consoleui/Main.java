package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
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
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    JpaSupplierRepository supplierRepository = new JpaSupplierRepository(entityManager);
    JpaPartSpecificationRepository partSpecificationRepository = new JpaPartSpecificationRepository(
        entityManager);
    CreatePartSpecification useCase = new CreatePartSpecification(
        partSpecificationRepository,
        supplierRepository);
    new CreatePartSpecificationConsoleUserInterface(
        useCase).run();
  }

}
