package edu.au.cpsc.inventory.partspecification.tests.repository;

import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataSupplierRepository;
import java.sql.SQLException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan("edu.au.cpsc.inventory")
@Transactional
public class SpringDataSupplierRepositoryTest extends SupplierRepositoryTest {

  private SpringDataSupplierRepository springDataSupplierRepository;

  @Autowired
  protected void setSpringDataSupplierRepository(
      SpringDataSupplierRepository springDataSupplierRepository) {
    this.springDataSupplierRepository = springDataSupplierRepository;
  }

  @Override
  protected SupplierRepository createRepository() throws SQLException {
    return springDataSupplierRepository;
  }
}
