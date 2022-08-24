package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.SQLException;

public class JpaCreatePartSpecificationTest extends CreatePartSpecificationTest {

  @Override
  protected void createRepositories() throws SQLException {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(
        "inventory-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    partSpecificationRepository = new JpaPartSpecificationRepository(entityManager);
    supplierRepository = new JpaSupplierRepository(entityManager);
  }
}
