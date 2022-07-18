package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
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
    List<PartSpecification> specs = useCase.getPartSpecifications();
    assertEquals(0, specs.size());
  }

  @Test
  public void given_one_part_specification_then_one_listed() {
    partSpecificationRepository.save(new PartSpecification());

    List<PartSpecification> specs = useCase.getPartSpecifications();

    assertEquals(1, specs.size());
  }

  @Test
  public void given_two_part_specification_then_two_listed() {
    partSpecificationRepository.save(new PartSpecification());
    partSpecificationRepository.save(new PartSpecification());

    List<PartSpecification> specs = useCase.getPartSpecifications();

    assertEquals(2, specs.size());
  }

  @Test
  public void given_no_part_specifications_when_part_saved_then_one_part_present_in_repository() {
    useCase.createPartSpecification(new PartSpecification());

    List<PartSpecification> specs = partSpecificationRepository.findAll();

    assertEquals(1, specs.size());
  }

  @Test
  public void given_a_part_specification_when_supplier_added_then_specification_has_supplier() {
    PartSpecification ps = new PartSpecification();
    useCase.createPartSpecification(ps);

    useCase.addSupplierToPartSpecification(ps, new Supplier());

    assertEquals(1, ps.getSuppliers().size());
  }

  @Test
  public void given_no_suppliers_then_none_listed() {
    List<Supplier> suppliers = useCase.getSuppliers();

    assertEquals(0, suppliers.size());
  }

  @Test
  public void given_one_supplier_then_one_listed() {
    supplierRepository.save(new Supplier());

    List<Supplier> suppliers = useCase.getSuppliers();

    assertEquals(1, suppliers.size());
  }

  @Test
  public void given_no_suppliers_when_one_created_then_one_in_repository() {
    useCase.createSupplier(new Supplier());
    assertEquals(1, supplierRepository.findAll().size());
  }

  @Test
  public void given_part_specification_then_id_is_null() {
    PartSpecification ps = new PartSpecification();

    assertNull(ps.getId());
  }

  @Test
  public void given_created_part_specification_the_id_not_null() {
    PartSpecification ps = new PartSpecification();
    useCase.createPartSpecification(ps);

    assertNotNull(ps.getId());
  }

  @Test
  public void given_two_created_part_specifications_their_ids_will_be_different() {
    PartSpecification ps1 = new PartSpecification();
    PartSpecification ps2 = new PartSpecification();
    useCase.createPartSpecification(ps1);
    useCase.createPartSpecification(ps2);

    assertFalse(ps1.getId().equals(ps2.getId()));
  }

}
