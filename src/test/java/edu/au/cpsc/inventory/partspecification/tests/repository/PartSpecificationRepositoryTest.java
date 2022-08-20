package edu.au.cpsc.inventory.partspecification.tests.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class PartSpecificationRepositoryTest {

  private PartSpecificationRepository repository;

  @BeforeEach
  public void setUp() throws SQLException {
    repository = createRepository();
  }

  @Test
  public void when_part_specification_already_saved_then_updated() {
    PartSpecification ps = new PartSpecification();
    ps.setName("original name");
    ps.setDescription("original description");
    Long id = repository.save(ps);

    ps.setName("new name");
    ps.setDescription("new description");
    repository.save(ps);
    PartSpecification psFromRepository = repository.findOne(id);

    assertEquals(id, psFromRepository.getId());
    assertEquals("new name", psFromRepository.getName());
    assertEquals("new description", psFromRepository.getDescription());
  }

  @Test
  public void when_part_specification_saved_twice_id_not_changed() {
    PartSpecification ps = new PartSpecification();
    repository.save(ps);
    Long oldId = ps.getId();
    repository.save(ps);
    assertEquals(oldId, ps.getId());
  }

  @Test
  public void when_part_specification_saved_id_returned() {
    PartSpecification ps = new PartSpecification();
    Long id = repository.save(ps);
    assertNotNull(id);
  }

  @Test
  public void when_part_specification_saved_id_set_in_original_part_specification_same_as_returned() {
    PartSpecification ps = new PartSpecification();
    Long id = repository.save(ps);
    assertNotNull(ps.getId());
    assertEquals(id, ps.getId());
  }

  @Test
  public void when_no_part_specifications_then_find_all_returns_empty_list() {
    List<PartSpecification> specifications = repository.findAll();
    assertEquals(0, specifications.size());
  }

  @Test
  public void when_part_specification_saved_then_returned_from_find_all() {
    PartSpecification ps = new PartSpecification();
    ps.setName("name");
    ps.setDescription("description");
    Long id = repository.save(ps);
    List<PartSpecification> specifications = repository.findAll();
    assertEquals(1, specifications.size());
    PartSpecification psFromRepository = specifications.get(0);
    assertEquals(id, psFromRepository.getId());
    assertEquals("name", psFromRepository.getName());
    assertEquals("description", psFromRepository.getDescription());
  }

  @Test
  public void when_no_part_specification_saved_then_null_returned_from_find_one() {
    PartSpecification ps = repository.findOne(Long.valueOf(1));
    assertNull(ps);
  }

  @Test
  public void when_part_specification_saved_then_returned_from_find_one() {
    PartSpecification ps = new PartSpecification();
    ps.setName("name");
    ps.setDescription("description");
    Long id = repository.save(ps);
    PartSpecification psFromRepository = repository.findOne(id);
    assertNotNull(psFromRepository);
    assertEquals(id, psFromRepository.getId());
    assertEquals("name", psFromRepository.getName());
    assertEquals("description", psFromRepository.getDescription());
  }

  @Test
  public void when_part_specification_found_by_id_twice_then_objects_same() {
    PartSpecification ps = new PartSpecification();
    Long id = repository.save(ps);

    var psFromRepository1 = repository.findOne(id);
    var psFromRepository2 = repository.findOne(id);

    assertSame(psFromRepository1, psFromRepository2);
  }

  @Test
  public void when_part_specification_found_by_find_all_then_objects_same() {
    PartSpecification ps = new PartSpecification();
    Long id = repository.save(ps);
    List<PartSpecification> specifications = repository.findAll();
    var psFromRepository = repository.findOne(id);
    assertSame(specifications.get(0), psFromRepository);
  }

  protected abstract PartSpecificationRepository createRepository() throws SQLException;

}
