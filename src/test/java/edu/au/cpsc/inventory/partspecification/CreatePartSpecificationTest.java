package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.au.cpsc.inventory.partspecification.CreatePartSpecification.PartSpecificationModel;
import edu.au.cpsc.inventory.partspecification.CreatePartSpecification.SupplierModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatePartSpecificationTest {

  private PartSpecificationRepository partSpecificationRepository;
  private CreatePartSpecification useCase;
  private SupplierRepository supplierRepository;

  @BeforeEach
  public void setUp() {
    partSpecificationRepository = new PartSpecificationRepository();
    supplierRepository = new SupplierRepository();
    useCase = new CreatePartSpecification(partSpecificationRepository, supplierRepository);
  }

  @Test
  public void given_no_part_specifications_then_none_listed() {
    var specs = useCase.getPartSpecifications();
    assertEquals(0, specs.size());
  }

  @Test
  public void given_one_part_specification_then_one_listed() {
    partSpecificationRepository.save(new PartSpecification());

    var specs = useCase.getPartSpecifications();

    assertEquals(1, specs.size());
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
    useCase.createPartSpecification(new PartSpecificationModel());

    var specs = partSpecificationRepository.findAll();

    assertEquals(1, specs.size());
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
    supplierRepository.save(new Supplier());

    var suppliers = useCase.getSuppliers();

    assertEquals(1, suppliers.size());
  }

  @Test
  public void given_no_suppliers_when_one_created_then_one_in_repository() {
    useCase.createSupplier(new SupplierModel());
    assertEquals(1, supplierRepository.findAll().size());
  }

  @Test
  public void given_part_specification_then_id_is_null() {
    PartSpecification ps = new PartSpecification();

    assertNull(ps.getId());
  }

  @Test
  public void given_created_part_specification_the_id_not_null() {
    PartSpecificationModel ps = new PartSpecificationModel();
    Long id = useCase.createPartSpecification(ps);

    assertNotNull(partSpecificationRepository.findOne(id));
  }

  @Test
  public void given_two_created_part_specifications_their_ids_will_be_different() {
    var ps1 = new PartSpecificationModel();
    var ps2 = new PartSpecificationModel();
    Long id1 = useCase.createPartSpecification(ps1);
    Long id2 = useCase.createPartSpecification(ps2);

    assertFalse(id1.equals(id2));
  }

}
