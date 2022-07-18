package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PartSpecificationRepositoryTest {

  @Test
  public void when_part_specification_saved_twice_id_not_changed() {
    PartSpecification ps = new PartSpecification();
    PartSpecificationRepository repository = new PartSpecificationRepository();
    repository.save(ps);
    Long oldId = ps.getId();
    repository.save(ps);
    assertEquals(oldId, ps.getId());
  }

}
