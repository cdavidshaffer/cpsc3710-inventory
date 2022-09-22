package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataPartSpecificationRepository;
import edu.au.cpsc.inventory.partspecification.repository.spring.SpringDataSupplierRepository;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Start the console user interface.  This class currently serves as documentation about the
 * start-up of the system.  Especially in terms of injecting dependencies.
 */
@Configuration
@Profile("console")
public class Main {

  /**
   * Start the console user interface.
   */
  @Bean
  public CommandLineRunner consoleUserInterface(SpringDataSupplierRepository supplierRepository,
      SpringDataPartSpecificationRepository partSpecificationRepository) {
    return args -> {
      CreatePartSpecification useCase = new CreatePartSpecification(
          partSpecificationRepository,
          supplierRepository);
      new CreatePartSpecificationConsoleUserInterface(
          useCase).run();
    };
  }

}
