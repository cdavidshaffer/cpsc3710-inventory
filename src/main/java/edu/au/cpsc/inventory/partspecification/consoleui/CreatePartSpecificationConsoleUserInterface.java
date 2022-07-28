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
    createPartSpecification.createSupplier(new CreatePartSpecification.DefaultSupplierModel());
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
      System.out.printf("%d) %s: %s\n", m.getId(), m.getName(), m.getDescription());
    }

    System.out.print("Enter id of the part specification to modify: ");
    final Long partSpecificationId = scanner.nextLong();
    scanner.nextLine();

    System.out.println("Suppliers: ");
    for (var m : createPartSpecification.getSuppliers()) {
      System.out.printf("%d\n", m.getId());
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
    var model = new CreatePartSpecification.DefaultPartSpecificationModel();
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
    System.out.println("c) Create part specification");
    System.out.println("a) Assign supplier to part specification");
    System.out.println("s) Create supplier");
    System.out.println("q) Quit");
  }

  private enum MenuResponse {
    QUIT('q'),
    CREATE_PART_SPECIFICATION('c'),
    ASSIGN_SUPPLIER('a'),
    INVALID('?'),
    CREATE_SUPPLIER('s');

    private final char inputCharacter;

    MenuResponse(char inputCharacter) {
      this.inputCharacter = inputCharacter;
    }

    public char getInputCharacter() {
      return inputCharacter;
    }
  }
}
