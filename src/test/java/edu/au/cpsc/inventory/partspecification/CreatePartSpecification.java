package edu.au.cpsc.inventory.partspecification;

import java.util.List;

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

  public void createPartSpecification(PartSpecification partSpecification) {
    partSpecificationRepository.save(partSpecification);
  }


  public void addSupplierToPartSpecification(PartSpecification ps, Supplier supplier) {
    ps.addSupplier(supplier);
  }

  public List<Supplier> getSuppliers() {
    return supplierRepository.findAll();
  }

  public void createSupplier(Supplier supplier) {
    supplierRepository.save(supplier);
  }
}
