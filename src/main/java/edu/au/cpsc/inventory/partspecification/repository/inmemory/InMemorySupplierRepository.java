package edu.au.cpsc.inventory.partspecification.repository.inmemory;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;

/**
 * I provide access to a collection of suppliers.  New suppliers can be added, and they will be
 * assigned unique ids.
 */
public class InMemorySupplierRepository extends InMemoryEntityRepository<Supplier> implements
    SupplierRepository {

}
