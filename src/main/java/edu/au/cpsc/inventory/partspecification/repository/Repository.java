package edu.au.cpsc.inventory.partspecification.repository;

import edu.au.cpsc.inventory.partspecification.entity.Entity;
import java.util.List;

public interface Repository<T extends Entity> {

  Long save(T entity);

  List<T> findAll();

  T findOne(Long id);
}
