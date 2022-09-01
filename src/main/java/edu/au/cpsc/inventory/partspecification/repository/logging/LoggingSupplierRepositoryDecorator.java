package edu.au.cpsc.inventory.partspecification.repository.logging;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.List;
import java.util.logging.Logger;

/**
 * I decorate all SupplierRepository methods with logging.
 */
public class LoggingSupplierRepositoryDecorator extends SupplierRepositoryDecorator {

  private static final Logger log = Logger.getLogger(
      LoggingSupplierRepositoryDecorator.class.getName());

  public LoggingSupplierRepositoryDecorator(SupplierRepository repository) {
    super(repository);
  }

  @Override
  public Long save(Supplier entity) {
    log.info("About to save supplier: " + entity.getName());
    Long returnValue = repository.save(entity);
    log.info("Completed save");
    return returnValue;
  }

  @Override
  public List<Supplier> findAll() {
    log.info("About to find all suppliers");
    return repository.findAll();
  }

  @Override
  public Supplier findOne(Long id) {
    log.info("About to find one with id " + id);
    return repository.findOne(id);
  }
}
