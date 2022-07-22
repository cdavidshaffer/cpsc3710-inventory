package edu.au.cpsc.inventory.partspecification;

public class Main {

  public static void main(String[] args) {
    new CreatePartSpecificationConsoleUI(
        new CreatePartSpecification(new PartSpecificationRepository(),
            new SupplierRepository())).run();
  }

}
