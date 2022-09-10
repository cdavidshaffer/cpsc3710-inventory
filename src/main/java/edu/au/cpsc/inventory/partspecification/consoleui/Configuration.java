package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.logging.LoggingSupplierRepositoryDecorator;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;

/**
 * Configuration of the console user interface.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

  /**
   * Create Part Specification use case instance.
   *
   * @param partSpecificationRepository the repository used to manage part specifications
   * @param supplierRepository          the repository used to manage suppliers
   * @return a use case
   */
  @Bean
  public CreatePartSpecification createPartSpecification(
      PartSpecificationRepository partSpecificationRepository,
      SupplierRepository supplierRepository) {
    return new CreatePartSpecification(
        partSpecificationRepository,
        supplierRepository);
  }

  /**
   * The part specification repository.
   *
   * @param entityManager an entity manager
   * @return the repository
   */
  @Bean
  public PartSpecificationRepository partSpecificationRepository(EntityManager entityManager) {
    return new JpaPartSpecificationRepository(entityManager);
  }

  /**
   * The supplier repository.
   *
   * @param entityManager an entity manager
   * @return the repository
   */
  @Bean
  public SupplierRepository supplierRepository(EntityManager entityManager) {
    return new LoggingSupplierRepositoryDecorator(new JpaSupplierRepository(entityManager));
  }

  /**
   * An entity manager.  DON'T DO THIS!  This is atypical of a Spring application as Spring wants to
   * manage your JPA sessions and transaction.  We'll fix this soon!
   *
   * @param entityManagerFactory the factory used to create our entity manager
   * @return an entity manager
   */
  @Bean
  public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }

  /**
   * An entity manager factory.  DON'T DO THIS!  This is atypical of a Spring application as Spring
   * wants to manage your JPA sessions and transaction.  We'll fix this soon!
   *
   * @return an entity manager factory
   */
  @Bean
  public EntityManagerFactory entityManagerFactory() {
    return Persistence.createEntityManagerFactory(
        "inventory");
  }

}
