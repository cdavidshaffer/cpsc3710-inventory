package edu.au.cpsc.inventory.partspecification.consoleui;

import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import java.util.Scanner;

/**
 * Console user interface for the part specification creation use case.
 */
public class CreatePartSpecificationConsoleUserInterface extends
    ConsoleUserInterface<CreatePartSpecificationConsoleUserInterface.MenuResponse> {

  private final Scanner scanner;
  private CreatePartSpecification createPartSpecification;

  public CreatePartSpecificationConsoleUserInterface(
      CreatePartSpecification createPartSpecification) {
    this.createPartSpecification = createPartSpecification;
    scanner = new Scanner(System.in);
  }

  @Override
  protected MenuResponse quitMenuResponse() {
    return MenuResponse.QUIT;
  }

  @Override
  protected void handleMenuSelection(MenuResponse selection) {
    if (selection == MenuResponse.INVALID) {
      System.out.println("Invalid menu selection");
    } else {
      selection.execute(createPartSpecification, scanner);
    }
  }

  @Override
  protected MenuResponse getMenuResponse() {
    System.out.print("Enter selection> ");
    String line = scanner.nextLine();
    for (MenuResponse candidate : MenuResponse.values()) {
      if (candidate.getInputCharacter() == line.charAt(0)) {
        return candidate;
      }
    }
    return MenuResponse.INVALID;
  }

  @Override
  protected void displayMainMenu() {
    System.out.println("\n\nMain menu");
    for (MenuResponse r : MenuResponse.values()) {
      if (r != MenuResponse.INVALID) {
        System.out.println(r);
      }
    }
  }

  /**
   * Possible menu selections.
   */
  protected enum MenuResponse {
    CREATE_PART_SPECIFICATION('c',
        "Create part specification",
        new CreatePartSpecificationAction()),
    LIST_PART_SPECIFICATIONS('l',
        "List part specifications",
        new ListPartSpecificationsAction()),
    ASSIGN_SUPPLIER('a',
        "Assign supplier to part specification",
        new AssignSupplierAction()),
    INVALID('?',
        "INVALID",
        null),
    CREATE_SUPPLIER('s',
        "Create supplier",
        new CreateSupplierAction()),
    QUIT('q',
        "Quit",
        new QuitAction());

    private final char inputCharacter;
    private final String description;
    private MenuAction action;

    MenuResponse(char inputCharacter, String description, MenuAction action) {
      this.inputCharacter
          = inputCharacter;
      this.description = description;
      this.action = action;
    }

    public char getInputCharacter() {
      return inputCharacter;
    }

    @Override
    public String toString() {
      return inputCharacter + ") " + description;
    }

    void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
      if (action != null) {
        action.execute(createPartSpecification, scanner);
      }
    }
  }

  /**
   * Menu actions.
   */
  public static interface MenuAction {

    void execute(CreatePartSpecification createPartSpecification, Scanner scanner);
  }

  /**
   * Create a supplier.
   */
  public static class CreateSupplierAction implements MenuAction {

    @Override
    public void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
      var model = new CreatePartSpecification.SupplierModel();
      System.out.print("Enter supplier name> ");
      String name = scanner.nextLine();
      model.setName(name);
      try {
        createPartSpecification.createSupplier(model);
        System.out.println("Created!");
      } catch (javax.validation.ConstraintViolationException ex) {
        System.out.println("\n\nFailed to create supplier:");
        for (var violation : ex.getConstraintViolations()) {
          System.out.println("\t" + violation.getPropertyPath() + " " + violation.getMessage());
        }
      }

    }
  }

  /**
   * Assign a supplier to a part specification.
   */
  public static class AssignSupplierAction implements MenuAction {

    @Override
    public void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
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
  }

  /**
   * Quit -- actually, this is detected by the main loop so we don't do anything here.
   */
  public static class QuitAction implements MenuAction {

    @Override
    public void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
      // do nothing!
    }
  }

  /**
   * List part specifications.
   */
  public static class ListPartSpecificationsAction implements MenuAction {

    @Override
    public void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
      System.out.println("Existing part specifications: ");
      for (var m : createPartSpecification.getPartSpecifications()) {
        System.out.printf("%s: %s\n", m.getName(), m.getDescription());
      }
    }
  }

  /**
   * Create a part specification.
   */
  public static class CreatePartSpecificationAction implements MenuAction {

    @Override
    public void execute(CreatePartSpecification createPartSpecification, Scanner scanner) {
      System.out.println("Create part\n");
      new ListPartSpecificationsAction().execute(createPartSpecification, scanner);
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
  }
}
