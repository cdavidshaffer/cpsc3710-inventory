package edu.au.cpsc.inventory.partspecification.repository.jpa;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import jakarta.persistence.EntityManager;

/**
 * Implementation of PartSpecificationRepository using JPA.
 */
public class JpaPartSpecificationRepository extends
    JpaRepository<PartSpecification> implements PartSpecificationRepository {

  public JpaPartSpecificationRepository(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected String getEntityName() {
    return "PartSpecification";
  }

  @Override
  protected Class<PartSpecification> getEntityClass() {
    return PartSpecification.class;

  }


}
