package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.util.Scanner;

/**
 * Console user interface for the part specification creation use case.
 */
public class CreatePartSpecificationConsoleUserInterface {

  private final Scanner scanner;
  private CreatePartSpecification createPartSpecification;

  public CreatePartSpecificationConsoleUserInterface(
      CreatePartSpecification createPartSpecification) {
    this.createPartSpecification = createPartSpecification;
    scanner = new Scanner(System.in);
  }

  /**
   * Execute this user interface's main loop.
   */
  public void run() {
    displayMainMenu();
    MenuResponse selection = getMenuResponse();
    while (selection != MenuResponse.QUIT) {
      handleMenuSelection(selection);
      displayMainMenu();
      selection = getMenuResponse();
    }
  }

  private void handleMenuSelection(MenuResponse selection) {
    switch (selection) {
      case QUIT:
        break;
      case CREATE_PART_SPECIFICATION:
        createPartSpecification();
        break;
      case ASSIGN_SUPPLIER:
        assignSupplier();
        break;
      case CREATE_SUPPLIER:
        createSupplier();
        break;
      case INVALID:
      default:
        System.out.println("Invalid menu selection");
    }
  }

  private void createSupplier() {
    var model = new CreatePartSpecification.SupplierModel();
    System.out.print("Enter supplier name> ");
    String name = scanner.nextLine();
    model.setName(name);
    createPartSpecification.createSupplier(model);
    System.out.println("Created!");
  }

  private void assignSupplier() {
    if (createPartSpecification.getSuppliers().isEmpty()) {
      System.out.println("No suppliers, create one first");
      return;
    }
    if (createPartSpecification.getPartSpecifications().isEmpty()) {
      System.out.println("No part specifications, create one first");
      return;
    }
    System.out.println("Create part\n");
    System.out.println("Existing part specifications: ");
    for (var m : createPartSpecification.getPartSpecifications()) {
      System.out.printf("%d) %s: %s%n", m.getId(), m.getName(), m.getDescription());
    }

    System.out.print("Enter id of the part specification to modify: ");
    final Long partSpecificationId = scanner.nextLong();
    scanner.nextLine();

    System.out.println("Suppliers: ");
    for (var m : createPartSpecification.getSuppliers()) {
      System.out.printf("%d) %s%n", m.getId(), m.getName());
    }

    System.out.print("Enter id of the supplier to add: ");
    final Long supplierId = scanner.nextLong();
    scanner.nextLine();

    createPartSpecification.addSupplierToPartSpecification(partSpecificationId, supplierId);
  }

  private void createPartSpecification() {
    System.out.println("Create part\n");
    System.out.println("Existing part specifications: ");
    for (var m : createPartSpecification.getPartSpecifications()) {
      System.out.printf("%s: %s\n", m.getName(), m.getDescription());
    }
    System.out.println("Are you sure you want to create a new one?");
    String response = scanner.nextLine();
    if (response.toLowerCase().equals("n")) {
      return;
    }
    System.out.print("Enter part specification name: ");
    String name = scanner.nextLine();
    System.out.print("Enter part specification description: ");
    String description = scanner.nextLine();
    var model = new CreatePartSpecification.PartSpecificationModel();
    model.setName(name);
    model.setDescription(description);
    createPartSpecification.createPartSpecification(model);
  }

  private MenuResponse getMenuResponse() {
    System.out.print("Enter selection> ");
    String line = scanner.nextLine();
    for (MenuResponse candidate : MenuResponse.values()) {
      if (candidate.getInputCharacter() == line.charAt(0)) {
        return candidate;
      }
    }
    return MenuResponse.INVALID;
  }

  private void displayMainMenu() {
    System.out.println("\n\nMain menu");
    for (MenuResponse r : MenuResponse.values()) {
      if (r != MenuResponse.INVALID) {
        System.out.println(r);
      }
    }
  }

  private enum MenuResponse {
    CREATE_PART_SPECIFICATION('c', "Create part specification"),
    ASSIGN_SUPPLIER('a', "Assign supplier to part specification"),
    INVALID('?', "INVALID"),
    CREATE_SUPPLIER('s', "Create supplier"),
    QUIT('q', "Quit");

    private final char inputCharacter;
    private final String description;

    MenuResponse(char inputCharacter, String description) {
      this.inputCharacter = inputCharacter;
      this.description = description;
    }

    public char getInputCharacter() {
      return inputCharacter;
    }

    @Override
    public String toString() {
      return inputCharacter + ") " + description;
    }
  }
}
