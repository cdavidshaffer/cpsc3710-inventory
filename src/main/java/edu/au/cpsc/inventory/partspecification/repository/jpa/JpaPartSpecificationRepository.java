package edu.au.cpsc.inventory.partspecification.repository.jpa;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of PartSpecificationRepository using JPA.
 */
public class JpaPartSpecificationRepository implements PartSpecificationRepository {

  private EntityManager entityManager;

  public JpaPartSpecificationRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Long save(PartSpecification entity) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(entity);
    transaction.commit();
    return entity.getId();
  }

  @Override
  public List<PartSpecification> findAll() {
    TypedQuery<PartSpecification> query = entityManager.createQuery(
        "SELECT ps from PartSpecification ps", PartSpecification.class);
    return query.getResultList();
  }

  @Override
  public PartSpecification findOne(Long id) {
    return entityManager.find(PartSpecification.class, id);
  }
}
