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

  /**
   * List all part specifications in my repository.
   *
   * @return list of part specifications as model objects
   */
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

  /**
   * A "supplier model" can provide data about a supplier that is relevant to this use case.
   */
  public interface SupplierModel {

    Long getId();

    void setId(Long id);
  }

  /**
   * A "part specification model" can provide data about a part specification that is relevant to
   * this use case.
   */
  public interface PartSpecificationModel {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Long getId();

    void setId(Long id);
  }

  /**
   * An implementation of SupplierModel that stores all properties in fields.
   */
  public static class DefaultSupplierModel implements SupplierModel {

    private Long id;

    @Override
    public Long getId() {
      return id;
    }

    @Override
    public void setId(Long id) {
      this.id = id;
    }
  }

  /**
   * An implementation of PartSpecificationModel that stores all properties in fields.
   */
  public static class DefaultPartSpecificationModel implements
      PartSpecificationModel {

    private String name;
    private String description;
    private Long id;

    @Override
    public String getName() {
      return name;
    }

    @Override
    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String getDescription() {
      return description;
    }

    @Override
    public void setDescription(String description) {
      this.description = description;
    }

    @Override
    public Long getId() {
      return id;
    }

    @Override
    public void setId(Long id) {
      this.id = id;
    }
  }
}
