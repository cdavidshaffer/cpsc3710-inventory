package edu.au.cpsc.inventory.partspecification.tests.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification.SupplierModel;
import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Transactional
public abstract class CreatePartSpecificationTest {

  protected PartSpecificationRepository partSpecificationRepository;
  protected SupplierRepository supplierRepository;
  private CreatePartSpecification useCase;

  @BeforeEach
  public void setUp() throws SQLException {
    createRepositories();
    useCase = new CreatePartSpecification(partSpecificationRepository, supplierRepository);
  }

  protected abstract void createRepositories() throws SQLException;

  @Test
  public void given_no_part_specifications_then_none_listed() {
    var specs = useCase.getPartSpecifications();
    assertEquals(0, specs.size());
  }

  @Test
  public void given_one_part_specification_then_one_listed() {
    PartSpecification partSpecification = new PartSpecification();
    partSpecification.setName("name");
    partSpecification.setDescription("description");
    Long id = partSpecificationRepository.save(partSpecification);

    var specs = useCase.getPartSpecifications();

    assertEquals(1, specs.size());
    assertEquals("name", specs.get(0).getName());
    assertEquals("description", specs.get(0).getDescription());
    assertEquals(id, specs.get(0).getId());
  }

  @Test
  public void given_two_part_specification_then_two_listed() {
    partSpecificationRepository.save(new PartSpecification());
    partSpecificationRepository.save(new PartSpecification());

    var specs = useCase.getPartSpecifications();

    assertEquals(2, specs.size());
  }

  @Test
  public void given_no_part_specifications_when_part_saved_then_one_part_present_in_repository() {
    CreatePartSpecification.PartSpecificationModel partSpecificationModel =
        new CreatePartSpecification.PartSpecificationModel();
    partSpecificationModel.setName("name");
    partSpecificationModel.setDescription("description");
    Long id = useCase.createPartSpecification(partSpecificationModel);

    List<PartSpecification> specs = partSpecificationRepository.findAll();

    assertEquals(1, specs.size());
    assertEquals(id, specs.get(0).getId());
    assertEquals("name", specs.get(0).getName());
    assertEquals("description", specs.get(0).getDescription());
  }

  @Test
  public void given_a_part_specification_then_specification_has_no_suppliers() {
    PartSpecification partSpecification = new PartSpecification();
    partSpecificationRepository.save(partSpecification);

    assertEquals(0,
        partSpecificationRepository.findOne(partSpecification.getId()).getSuppliers().size());
  }

  @Test
  public void given_a_part_specification_when_supplier_added_then_specification_has_supplier() {
    PartSpecification partSpecification = new PartSpecification();
    partSpecificationRepository.save(partSpecification);
    Supplier supplier = new Supplier();
    supplier.setName("name");
    supplierRepository.save(supplier);

    useCase.addSupplierToPartSpecification(partSpecification.getId(), supplier.getId());

    assertEquals(1,
        partSpecificationRepository.findOne(partSpecification.getId()).getSuppliers().size());
  }

  @Test
  public void given_no_suppliers_then_none_listed() {
    var suppliers = useCase.getSuppliers();

    assertEquals(0, suppliers.size());
  }

  @Test
  public void given_one_supplier_then_one_listed() {
    Supplier supplier = new Supplier();
    supplier.setName("name");
    Long id = supplierRepository.save(supplier);

    var suppliers = useCase.getSuppliers();

    assertEquals(1, suppliers.size());
    assertEquals(id, suppliers.get(0).getId());
  }

  @Test
  public void given_no_suppliers_when_one_created_then_one_in_repository() {
    SupplierModel supplierModel = new SupplierModel();
    supplierModel.setName("name");
    useCase.createSupplier(supplierModel);
    assertEquals(1, supplierRepository.findAll().size());
  }

  @Test
  public void given_part_specification_then_id_is_null() {
    PartSpecification ps = new PartSpecification();

    assertNull(ps.getId());
  }

  @Test
  public void given_created_part_specification_the_id_not_null() {
    CreatePartSpecification.PartSpecificationModel ps =
        new CreatePartSpecification.PartSpecificationModel();
    Long id = useCase.createPartSpecification(ps);

    assertNotNull(partSpecificationRepository.findOne(id));
  }

  @Test
  public void given_two_created_part_specifications_their_ids_will_be_different() {
    var ps1 = new CreatePartSpecification.PartSpecificationModel();
    var ps2 = new CreatePartSpecification.PartSpecificationModel();
    Long id1 = useCase.createPartSpecification(ps1);
    Long id2 = useCase.createPartSpecification(ps2);

    assertFalse(id1.equals(id2));
  }

  @Test
  public void given_supplier_with_null_name_when_saved_then_exception_thrown() {
    CreatePartSpecification.SupplierModel model = new CreatePartSpecification.SupplierModel();
    assertThrows(ConstraintViolationException.class, () -> useCase.createSupplier(model));
  }

  @Test
  public void given_supplier_with_whitespace_name_when_saved_then_exception_thrown() {
    CreatePartSpecification.SupplierModel model = new CreatePartSpecification.SupplierModel();
    model.setName("  \t\t\n\n\r\r  ");
    assertThrows(ConstraintViolationException.class, () -> useCase.createSupplier(model));
  }

}
