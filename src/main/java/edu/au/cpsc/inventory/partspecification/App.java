package edu.au.cpsc.inventory.partspecification;

import edu.au.cpsc.inventory.partspecification.repository.PartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.SupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application.
 */
@SpringBootApplication
@ComponentScan("edu.au.cpsc.inventory")
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  protected CreatePartSpecification createPartSpecification(
      PartSpecificationRepository partSpecificationRepository,
      SupplierRepository supplierRepository) {
    return new CreatePartSpecification(partSpecificationRepository, supplierRepository);
  }
}
