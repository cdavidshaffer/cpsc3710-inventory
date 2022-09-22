package edu.au.cpsc.inventory.partspecification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
