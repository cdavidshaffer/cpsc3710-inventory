package edu.au.cpsc.inventory.partspecification.repository.jpa;

import edu.au.cpsc.inventory.partspecification.entity.Entity;
import edu.au.cpsc.inventory.partspecification.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * My concrete subclasses are repositories.  I provide JPA implementations of the three
 * repository-independent methods.
 *
 * @param <T> type of Entity stored in this repository
 */
public abstract class JpaRepository<T extends Entity> implements Repository<T> {

  protected EntityManager entityManager;

  public JpaRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Long save(T entity) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      entityManager.persist(entity);
      transaction.commit();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }
    }
    return entity.getId();
  }

  @Override
  public List<T> findAll() {
    TypedQuery<T> query = entityManager.createQuery(
        "SELECT e from " + getEntityName() + " e", getEntityClass());
    return query.getResultList();
  }

  protected abstract Class<T> getEntityClass();

  protected abstract String getEntityName();

  @Override
  public T findOne(Long id) {
    return entityManager.find(getEntityClass(), id);
  }
}
