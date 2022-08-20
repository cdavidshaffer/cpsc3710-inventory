package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.SQLException;

public class JpaPartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  @Override
  protected PartSpecificationRepository createRepository() throws SQLException {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return new JpaPartSpecificationRepository(entityManager);
  }
}
