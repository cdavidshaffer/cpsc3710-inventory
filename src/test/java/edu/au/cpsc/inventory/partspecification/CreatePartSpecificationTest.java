package edu.au.cpsc.inventory.partspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreatePartSpecificationTest {

  private PartSpecificationRepository repository;
  private CreatePartSpecification useCase;

  @BeforeEach
  public void setUp() {
    repository = new PartSpecificationRepository();
    useCase = new CreatePartSpecification(repository);
  }

  @Test
  public void given_no_part_specifications_then_none_listed() {
    List<PartSpecification> specs = useCase.getPartSpecifications();
    assertEquals(0, specs.size());
  }

  @Test
  public void given_one_part_specification_then_one_listed() {
    repository.save(new PartSpecification());

    List<PartSpecification> specs = useCase.getPartSpecifications();

    assertEquals(1, specs.size());
  }

  @Test
  public void given_two_part_specification_then_two_listed() {
    repository.save(new PartSpecification());
    repository.save(new PartSpecification());

    List<PartSpecification> specs = useCase.getPartSpecifications();

    assertEquals(2, specs.size());
  }

  @Test
  public void given_no_part_specifications_when_part_saved_then_one_part_present_in_repository() {
    useCase.save(new PartSpecification());

    List<PartSpecification> specs = repository.findAll();

    assertEquals(1, specs.size());
  }

}
