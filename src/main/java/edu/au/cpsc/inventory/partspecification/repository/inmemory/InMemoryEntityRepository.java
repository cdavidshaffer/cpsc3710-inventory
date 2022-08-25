package edu.au.cpsc.inventory.partspecification.repository.inmemory;

import edu.au.cpsc.inventory.partspecification.entity.Entity;
import edu.au.cpsc.inventory.partspecification.repository.Repository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * I implement the Repository interface by storing objects in an in-memory collection.
 *
 * @param <T> type of Entity that will be stored in this repository
 */
public class InMemoryEntityRepository<T extends Entity> implements Repository<T> {

  protected List<T> entities;
  protected long lastId;
  private Validator validator;

  /**
   * Create an in-memory repository that uses the supplied validator to validate entities on save.
   *
   * @param validator the validator to use during save operations
   */
  public InMemoryEntityRepository(Validator validator) {
    this.validator = validator;
    entities = new ArrayList<T>();
    lastId = 0;
  }

  private Long nextId() {
    return Long.valueOf(lastId++);
  }

  private void ensureId(T entity) {
    if (entity.getId() != null) {
      return;
    }
    entity.setId(nextId());
  }

  /**
   * Store the specified part specification to this repository.  If the specification does not have
   * an id, one will be assigned.
   *
   * @param entity the part specification to add
   * @return the id of the object to save
   */
  @Override
  public Long save(T entity) {
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
    if (!constraintViolations.isEmpty()) {
      throw new ConstraintViolationException(constraintViolations);
    }
    ensureId(entity);
    entities.add(entity);
    return entity.getId();
  }

  /**
   * Return a list of all part specifications that have been saved in this repository.
   *
   * @return all part specifications that have been saved in this repository
   */
  @Override
  public List<T> findAll() {
    return entities;
  }

  /**
   * Given an id, return the part specification with that id or null if that part specification does
   * not exist.
   *
   * @param id the id of the part specification to find
   * @return the part specification with that id or null if there is none
   */
  @Override
  public T findOne(Long id) {
    for (T ps : entities) {
      if (ps.getId().equals(id)) {
        return ps;
      }
    }
    return null;
  }
}
