package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaSupplierRepositoryTest extends SupplierRepositoryTest {

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return new JpaSupplierRepository(entityManager);
  }
}
