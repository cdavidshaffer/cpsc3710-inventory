package edu.au.cpsc.inventory.partspecification;

import java.util.List;

/**
 * Implementation of the create part specification use case.  Supports listing and create part
 * specifications as well as listing and creating suppliers.
 */
public class CreatePartSpecification {

  private PartSpecificationRepository partSpecificationRepository;
  private SupplierRepository supplierRepository;

  public CreatePartSpecification(PartSpecificationRepository partSpecificationRepository,
      SupplierRepository supplierRepository) {
    this.partSpecificationRepository = partSpecificationRepository;
    this.supplierRepository = supplierRepository;
  }

  public List<PartSpecification> getPartSpecifications() {
    return partSpecificationRepository.findAll();
  }

  /**
   * Create a part description by adding it to my part description repository thereby assigning it
   * an id.
   *
   * @param partSpecification the part specification to be created
   */
  public void createPartSpecification(PartSpecification partSpecification) {
    partSpecificationRepository.save(partSpecification);
  }

  /**
   * Given a part specification, add a supplier to its list of suppliers.
   *
   * @param ps       the part specification to be modified
   * @param supplier the supplier to be added to the part specification
   */
  public void addSupplierToPartSpecification(PartSpecification ps, Supplier supplier) {
    ps.addSupplier(supplier);
  }

  public List<Supplier> getSuppliers() {
    return supplierRepository.findAll();
  }

  /**
   * Create a supplier by adding it to my supplier repository.
   *
   * @param supplier the supplier to be saved.
   */
  public void createSupplier(Supplier supplier) {
    supplierRepository.save(supplier);
  }
}
