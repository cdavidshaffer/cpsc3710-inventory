package edu.au.cpsc.inventory.partspecification.repository.logging;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.List;

/**
 * Abstract decorator.
 */
public abstract class SupplierRepositoryDecorator implements SupplierRepository {

  protected SupplierRepository repository;

  public SupplierRepositoryDecorator(SupplierRepository repository) {
    this.repository = repository;
  }

  @Override
  public Long save(Supplier entity) {
    return repository.save(entity);
  }

  @Override
  public List<Supplier> findAll() {
    return repository.findAll();
  }

  @Override
  public Supplier findOne(Long id) {
    return repository.findOne(id);
  }
}
