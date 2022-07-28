package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.inmemory.InMemoryPartSpecificationRepository;
import org.junit.jupiter.api.Test;

public class PartSpecificationRepositoryTest {

  @Test
  public void when_part_specification_saved_twice_id_not_changed() {
    PartSpecification ps = new PartSpecification();
    InMemoryPartSpecificationRepository repository = new InMemoryPartSpecificationRepository();
    repository.save(ps);
    Long oldId = ps.getId();
    repository.save(ps);
    assertEquals(oldId, ps.getId());
  }

}
