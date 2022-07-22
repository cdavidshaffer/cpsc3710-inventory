package edu.au.cpsc.inventory.partspecification;

import java.util.ArrayList;
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

  public List<PartSpecificationModel> getPartSpecifications() {
    List<PartSpecificationModel> result = new ArrayList<>();
    for (var ps : partSpecificationRepository.findAll()) {
      result.add(partSpecificationToModel(ps));
    }
    return result;
  }

  /**
   * Create a part description by adding it to my part description repository thereby assigning it
   * an id.
   *
   * @param partSpecificationModel the model for the part specification to be created
   * @return
   */
  public Long createPartSpecification(PartSpecificationModel partSpecificationModel) {
    PartSpecification partSpecification = modelToPartSpecification(partSpecificationModel);
    return partSpecificationRepository.save(partSpecification);
  }

  public List<SupplierModel> getSuppliers() {
    var result = new ArrayList<SupplierModel>();
    for (var supplier : supplierRepository.findAll()) {
      result.add(supplierToModel(supplier));
    }
    return result;
  }

  /**
   * Given a part specification, add a supplier to its list of suppliers.
   *
   * @param partSpecificationId the id of the part specification to be modified
   * @param supplierId          the id of the supplier to be added to the part specification
   */
  public void addSupplierToPartSpecification(Long partSpecificationId, Long supplierId) {
    var ps = partSpecificationRepository.findOne(partSpecificationId);
    var s = supplierRepository.findOne(supplierId);
    ps.addSupplier(s);
  }

  private PartSpecificationModel partSpecificationToModel(PartSpecification ps) {
    PartSpecificationModel partSpecificationModel = new DefaultPartSpecificationModel();
    partSpecificationModel.setName(ps.getName());
    partSpecificationModel.setDescription(ps.getDescription());
    partSpecificationModel.setId(ps.getId());
    return partSpecificationModel;
  }

  private PartSpecification modelToPartSpecification(PartSpecificationModel psm) {
    PartSpecification partSpecification = new PartSpecification();
    partSpecification.setName(psm.getName());
    partSpecification.setDescription(psm.getDescription());
    return partSpecification;
  }

  private SupplierModel supplierToModel(Supplier s) {
    SupplierModel supplierModel = new DefaultSupplierModel();
    supplierModel.setId(s.getId());
    return supplierModel;
  }

  private Supplier modelToSupplier(SupplierModel sm) {
    return new Supplier();
  }


  /**
   * Create a supplier by adding it to my supplier repository.
   *
   * @param supplier the supplier to be saved.
   */
  public void createSupplier(SupplierModel supplier) {
    supplierRepository.save(modelToSupplier(supplier));
  }

}
