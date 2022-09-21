package edu.au.cpsc.inventory.partspecification.repository.spring;

import edu.au.cpsc.inventory.partspecification.entity.PartSpecification;
import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Spring data implementation of {@link PartSpecificationRepository}.
 */
@Repository
public class SpringDataPartSpecificationRepository implements PartSpecificationRepository {

  private SpringDataPartSpecificationCrudRepository springDataPartSpecificationCrudRepository;

  public SpringDataPartSpecificationRepository(
      SpringDataPartSpecificationCrudRepository springDataPartSpecificationCrudRepository) {
    this.springDataPartSpecificationCrudRepository = springDataPartSpecificationCrudRepository;
  }

  @Override
  public Long save(PartSpecification entity) {
    return springDataPartSpecificationCrudRepository.save(entity).getId();
  }

  @Override
  public List<PartSpecification> findAll() {
    List<PartSpecification> result = new ArrayList<>();
    for (PartSpecification s : springDataPartSpecificationCrudRepository.findAll()) {
      result.add(s);
    }
    return result;
  }

  @Override
  public PartSpecification findOne(Long id) {
    return springDataPartSpecificationCrudRepository.findById(id).orElse(null);
  }
}
