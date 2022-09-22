package edu.au.cpsc.inventory.partspecification.repository.spring;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring data implementation of SupplierRepository.
 */
@Repository
public class SpringDataSupplierRepository implements SupplierRepository {

  private SpringDataSupplierCrudRepository springDataSupplierCrudRepository;

  public SpringDataSupplierRepository(
      SpringDataSupplierCrudRepository springDataSupplierCrudRepository) {
    this.springDataSupplierCrudRepository = springDataSupplierCrudRepository;
  }

  @Override
  public Long save(Supplier entity) {
    return springDataSupplierCrudRepository.save(entity).getId();
  }

  @Override
  public List<Supplier> findAll() {
    List<Supplier> result = new ArrayList<>();
    for (Supplier s : springDataSupplierCrudRepository.findAll()) {
      result.add(s);
    }
    return result;
  }

  @Override
  public Supplier findOne(Long id) {
    return springDataSupplierCrudRepository.findById(id).orElse(null);
  }
  
}
