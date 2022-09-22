package edu.au.cpsc.inventory.partspecification.tests.usecase;

import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataSupplierRepository;
import java.sql.SQLException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@Transactional
public class SpringDataCreatePartSpecificationTest extends CreatePartSpecificationTest {

  private SpringDataPartSpecificationRepository springDataPartSpecificationRepository;
  private SpringDataSupplierRepository springDataSupplierRepository;

  @Autowired
  public void setSpringDataPartSpecificationRepository(
      SpringDataPartSpecificationRepository springDataPartSpecificationRepository) {
    this.springDataPartSpecificationRepository = springDataPartSpecificationRepository;
  }

  @Autowired
  protected void setSpringDataSupplierRepository(
      SpringDataSupplierRepository springDataSupplierRepository) {
    this.springDataSupplierRepository = springDataSupplierRepository;
  }

  @Override
  protected void createRepositories() throws SQLException {
    partSpecificationRepository = springDataPartSpecificationRepository;
    supplierRepository = springDataSupplierRepository;
  }
}
