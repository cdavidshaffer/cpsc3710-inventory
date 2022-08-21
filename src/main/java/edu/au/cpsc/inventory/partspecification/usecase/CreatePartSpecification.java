package edu.au.cpsc.inventory.partspecification.usecase;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
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

  /**
   * List all part specifications in my repository.
   *
   * @return list of part specifications as model objects
   */
  public List<PartSpecificationModel> getPartSpecifications() {
    List<PartSpecificationModel> result = new ArrayList<>();
    for (PartSpecification ps : partSpecificationRepository.findAll()) {
      result.add(partSpecificationToModel(ps));
    }
    return result;
  }

  /**
   * Create a part description by adding it to my part description repository thereby assigning it
   * an id.
   *
   * @param partSpecificationModel the model for the part specification to be created
   * @return the id of the created part specification
   */
  public Long createPartSpecification(PartSpecificationModel partSpecificationModel) {
    PartSpecification partSpecification = modelToPartSpecification(partSpecificationModel);
    return partSpecificationRepository.save(partSpecification);
  }

  /**
   * List all suppliers in my repository.
   *
   * @return list of suppliers as model objects
   */
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
    PartSpecification ps = partSpecificationRepository.findOne(partSpecificationId);
    var s = supplierRepository.findOne(supplierId);
    ps.addSupplier(s);
    partSpecificationRepository.save(ps);
  }

  private PartSpecificationModel partSpecificationToModel(PartSpecification ps) {
    PartSpecificationModel partSpecificationModel = new PartSpecificationModel();
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
    SupplierModel supplierModel = new SupplierModel();
    supplierModel.setId(s.getId());
    supplierModel.setName(s.getName());
    return supplierModel;
  }

  private Supplier modelToSupplier(SupplierModel sm) {
    Supplier supplier = new Supplier();
    supplier.setName(sm.getName());
    return supplier;
  }


  /**
   * Create a supplier by adding it to my supplier repository.
   *
   * @param supplier the supplier to be saved.
   */
  public void createSupplier(SupplierModel supplier) {
    supplierRepository.save(modelToSupplier(supplier));
  }

  /**
   * A "supplier model" can provide data about a supplier that is relevant to this use case.
   */
  public static class SupplierModel {

    private Long id;
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }
  }

  /**
   * A "part specification model" can provide data about a part specification that is relevant to
   * this use case.
   */
  public static class PartSpecificationModel {

    private String name;
    private String description;
    private Long id;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }
  }

}
