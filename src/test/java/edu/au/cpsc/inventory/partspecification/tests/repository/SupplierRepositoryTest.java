package edu.au.cpsc.inventory.partspecification.tests.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.au.cpsc.inventory.partspecification.entity.Supplier;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class SupplierRepositoryTest {

  private SupplierRepository repository;

  @BeforeEach
  public void setUp() throws SQLException {
    repository = createRepository();
  }

  @Test
  public void when_supplier_already_saved_then_updated() throws SQLException {
    Supplier supplier = new Supplier();
    supplier.setName("original name");
    Long id = repository.save(supplier);

    supplier.setName("new name");
    repository.save(supplier);
    Supplier supplierFromRepository = repository.findOne(id);

    assertEquals(id, supplierFromRepository.getId());
    assertEquals("new name", supplierFromRepository.getName());
  }

  @Test
  public void when_supplier_saved_twice_id_not_changed() throws SQLException {
    Supplier supplier = new Supplier();
    repository.save(supplier);
    Long oldId = supplier.getId();
    repository.save(supplier);
    assertEquals(oldId, supplier.getId());
  }

  @Test
  public void when_supplier_saved_id_returned() throws SQLException {
    Supplier supplier = new Supplier();
    Long id = repository.save(supplier);
    assertNotNull(id);
  }

  @Test
  public void when_supplier_saved_id_set_in_original_supplier_same_as_returned()
      throws SQLException {
    Supplier supplier = new Supplier();
    Long id = repository.save(supplier);
    assertNotNull(supplier.getId());
    assertEquals(id, supplier.getId());
  }

  @Test
  public void when_no_suppliers_then_find_all_returns_empty_list() throws SQLException {
    List<Supplier> specifications = repository.findAll();
    assertEquals(0, specifications.size());
  }

  @Test
  public void when_supplier_saved_then_returned_from_find_all() throws SQLException {
    Supplier supplier = new Supplier();
    supplier.setName("name");
    Long id = repository.save(supplier);
    List<Supplier> specifications = repository.findAll();
    assertEquals(1, specifications.size());
    Supplier supplierFromRepository = specifications.get(0);
    assertEquals(id, supplierFromRepository.getId());
    assertEquals("name", supplierFromRepository.getName());
  }

  @Test
  public void when_no_supplier_saved_then_null_returned_from_find_one()
      throws SQLException {
    Supplier supplier = repository.findOne(Long.valueOf(1));
    assertNull(supplier);
  }

  @Test
  public void when_supplier_saved_then_returned_from_find_one() throws SQLException {
    Supplier supplier = new Supplier();
    supplier.setName("name");
    Long id = repository.save(supplier);
    Supplier supplierFromRepository = repository.findOne(id);
    assertNotNull(supplierFromRepository);
    assertEquals(id, supplierFromRepository.getId());
    assertEquals("name", supplierFromRepository.getName());
  }

  protected abstract SupplierRepository createRepository() throws SQLException;

}
