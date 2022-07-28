package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

public abstract class PartSpecificationRepositoryTest {

  @Test
  public void when_part_specification_saved_twice_id_not_changed() throws SQLException {
    PartSpecification ps = new PartSpecification();
    PartSpecificationRepository repository = createRepository();
    repository.save(ps);
    Long oldId = ps.getId();
    repository.save(ps);
    assertEquals(oldId, ps.getId());
  }

  @Test
  public void when_part_specification_saved_id_returned() throws SQLException {
    PartSpecification ps = new PartSpecification();
    PartSpecificationRepository repository = createRepository();
    Long id = repository.save(ps);
    assertNotNull(id);
  }

  @Test
  public void when_no_part_specifications_then_find_all_returns_empty_list() throws SQLException {
    PartSpecificationRepository repository = createRepository();
    List<PartSpecification> specifications = repository.findAll();
    assertEquals(0, specifications.size());
  }

  @Test
  public void when_part_specification_saved_then_returned_from_find_all() throws SQLException {
    PartSpecification ps = new PartSpecification();
    PartSpecificationRepository repository = createRepository();
    repository.save(ps);
    List<PartSpecification> specifications = repository.findAll();
    assertEquals(1, specifications.size());
  }

  @Test
  public void when_no_part_specification_saved_then_null_returned_from_find_one()
      throws SQLException {
    PartSpecificationRepository repository = createRepository();
    PartSpecification ps = repository.findOne(Long.valueOf(1));
    assertNull(ps);
  }

  @Test
  public void when_part_specification_saved_then_returned_from_find_one() throws SQLException {
    PartSpecification ps = new PartSpecification();
    PartSpecificationRepository repository = createRepository();
    Long id = repository.save(ps);
    PartSpecification ps2 = repository.findOne(id);
    assertNotNull(ps2);
  }

  protected abstract PartSpecificationRepository createRepository() throws SQLException;

}
