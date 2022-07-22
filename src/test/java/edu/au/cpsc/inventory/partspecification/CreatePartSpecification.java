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
      result.add(PartSpecificationModel.from(ps));
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
    PartSpecification partSpecification = partSpecificationModel.asPartSpecification();
    return partSpecificationRepository.save(partSpecification);
  }

  public List<SupplierModel> getSuppliers() {
    var result = new ArrayList<SupplierModel>();
    for (var supplier : supplierRepository.findAll()) {
      result.add(SupplierModel.from(supplier));
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


  /**
   * Create a supplier by adding it to my supplier repository.
   *
   * @param supplier the supplier to be saved.
   */
  public void createSupplier(SupplierModel supplier) {
    supplierRepository.save(supplier.asSupplier());
  }

  public static class PartSpecificationModel {

    public static PartSpecificationModel from(PartSpecification ps) {
      return new PartSpecificationModel();
    }

    private String name;
    private String description;

    public PartSpecification asPartSpecification() {
      return new PartSpecification();
    }


  }

  public static class SupplierModel {

    public static SupplierModel from(Supplier supplier) {
      return new SupplierModel();
    }

    public Supplier asSupplier() {
      return new Supplier();
    }
  }
}
