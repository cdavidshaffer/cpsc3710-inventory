package edu.au.cpsc.inventory.partspecification.repository.jpa;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of SupplierRepository using JPA.
 */
public class JpaSupplierRepository implements SupplierRepository {

  private final EntityManager entityManager;

  public JpaSupplierRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Long save(Supplier entity) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(entity);
    transaction.commit();
    return entity.getId();
  }

  @Override
  public List<Supplier> findAll() {
    TypedQuery<Supplier> query = entityManager.createQuery("SELECT supplier FROM Supplier supplier",
        Supplier.class);
    return query.getResultList();
  }

  @Override
  public Supplier findOne(Long id) {
    return entityManager.find(Supplier.class, id);
  }

  @Override
  public List<Supplier> findForPartSpecificationId(Long id) {
    return null;
  }
}
