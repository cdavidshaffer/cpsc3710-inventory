package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemorySupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatePartSpecificationTest {

  private InMemoryPartSpecificationRepository partSpecificationRepository;
  private CreatePartSpecification useCase;
  private InMemorySupplierRepository supplierRepository;

  @BeforeEach
  public void setUp() {
    partSpecificationRepository = new InMemoryPartSpecificationRepository();
    supplierRepository = new InMemorySupplierRepository();
    useCase = new CreatePartSpecification(partSpecificationRepository, supplierRepository);
  }

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
    CreatePartSpecification.PartSpecificationModel partSpecificationModel = new CreatePartSpecification.DefaultPartSpecificationModel();
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
    Long id = supplierRepository.save(new Supplier());

    var suppliers = useCase.getSuppliers();

    assertEquals(1, suppliers.size());
    assertEquals(id, suppliers.get(0).getId());
  }

  @Test
  public void given_no_suppliers_when_one_created_then_one_in_repository() {
    useCase.createSupplier(new CreatePartSpecification.DefaultSupplierModel());
    assertEquals(1, supplierRepository.findAll().size());
  }

  @Test
  public void given_part_specification_then_id_is_null() {
    PartSpecification ps = new PartSpecification();

    assertNull(ps.getId());
  }

  @Test
  public void given_created_part_specification_the_id_not_null() {
    CreatePartSpecification.PartSpecificationModel ps = new CreatePartSpecification.DefaultPartSpecificationModel();
    Long id = useCase.createPartSpecification(ps);

    assertNotNull(partSpecificationRepository.findOne(id));
  }

  @Test
  public void given_two_created_part_specifications_their_ids_will_be_different() {
    var ps1 = new CreatePartSpecification.DefaultPartSpecificationModel();
    var ps2 = new CreatePartSpecification.DefaultPartSpecificationModel();
    Long id1 = useCase.createPartSpecification(ps1);
    Long id2 = useCase.createPartSpecification(ps2);

    assertFalse(id1.equals(id2));
  }

}
