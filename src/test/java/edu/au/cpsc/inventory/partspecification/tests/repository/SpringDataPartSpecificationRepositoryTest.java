package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataPartSpecificationRepository;
import java.sql.SQLException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Transactional
public class SpringDataPartSpecificationRepositoryTest extends PartSpecificationRepositoryTest {

  private SpringDataPartSpecificationRepository springDataPartSpecificationRepository;

  @Autowired
  public void setSpringDataPartSpecificationRepository(
      SpringDataPartSpecificationRepository springDataPartSpecificationRepository) {
    this.springDataPartSpecificationRepository = springDataPartSpecificationRepository;
  }

  @Override
  protected PartSpecificationRepository createRepository() throws SQLException {
    return springDataPartSpecificationRepository;
  }
}
