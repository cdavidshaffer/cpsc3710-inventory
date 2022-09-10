package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.sql.SQLException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Start the console user interface.  This class currently serves as documentation about the
 * start-up of the system.  Especially in terms of injecting dependencies.
 */
@SpringBootApplication
public class Main {

  /**
   * Start the console user interface.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws SQLException {
    SpringApplication.run(Main.class, args);
  }

  @Bean
  protected CommandLineRunner consoleUserInterface(
      CreatePartSpecification createPartSpecification) {
    return (args) -> new CreatePartSpecificationConsoleUserInterface(createPartSpecification).run();
  }


}
