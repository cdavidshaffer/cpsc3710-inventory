package edu.au.cpsc.inventory.partspecification.repository.jpa;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import javax.persistence.EntityManager;

/**
 * Implementation of SupplierRepository using JPA.
 */
public class JpaSupplierRepository extends
    JpaRepository<Supplier> implements SupplierRepository {

  public JpaSupplierRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected String getEntityName() {
    return "Supplier";
  }

  @Override
  protected Class<Supplier> getEntityClass() {
    return Supplier.class;
  }

}