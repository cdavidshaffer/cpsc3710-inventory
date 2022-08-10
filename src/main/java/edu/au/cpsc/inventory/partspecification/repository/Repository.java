package edu.au.cpsc.inventory.partspecification.repository;

import edu.au.cpsc.inventory.partspecification.entity.Entity;
import java.util.List;

/**
 * Repositories can store and retrieve objects.  I define methods common to all repositories but my
 * subtypes may include additional (particularly search-related) methods.
 *
 * @param <T> the type of Entity stored in this repository.
 */
public interface Repository<T extends Entity> {

  Long save(T entity);

  List<T> findAll();

  T findOne(Long id);
}
