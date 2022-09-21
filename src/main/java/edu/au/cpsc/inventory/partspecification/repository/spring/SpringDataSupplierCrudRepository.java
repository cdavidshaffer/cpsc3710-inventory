package edu.au.cpsc.inventory.partspecification.repository.spring;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring data supplier repository but does not implement our SupplierRepository interface.  See
 * {@link SpringDataSupplierRepository} for an adapter class.
 */
@Repository
public interface SpringDataSupplierCrudRepository extends CrudRepository<Supplier, Long> {

}
