package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.jpa.JpaSupplierRepository;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
