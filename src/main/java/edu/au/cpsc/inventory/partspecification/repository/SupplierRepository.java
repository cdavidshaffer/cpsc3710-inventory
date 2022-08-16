package edu.au.cpsc.inventory.partspecification.repository;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import java.util.List;

/**
 * I can store and retrieve Supplier instances.
 */
public interface SupplierRepository extends Repository<Supplier> {

  List<Supplier> findForPartSpecificationId(Long id);
}
