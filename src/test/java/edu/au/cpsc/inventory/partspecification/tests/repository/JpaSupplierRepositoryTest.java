package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.SQLException;

public class JpaSupplierRepositoryTest extends SupplierRepositoryTest {

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return new JpaSupplierRepository(entityManager);
  }
}
