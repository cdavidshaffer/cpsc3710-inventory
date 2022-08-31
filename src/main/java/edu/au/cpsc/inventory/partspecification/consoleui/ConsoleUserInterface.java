package edu.au.cpsc.inventory.partspecification.consoleui;

/**
 * I am an abstract menu-driven console UI.  I provide the main application loop ({@link #run()}).
 *
 * @param <MenuResponseT> The type used to represent menu responses.  Can be an enum or class of any
 *                        type.
 */
public abstract class ConsoleUserInterface<MenuResponseT> {

  /**
   * Execute this user interface's main loop.
   */
  public void run() {
    displayMainMenu();
    MenuResponseT selection = getMenuResponse();
    while (selection != quitMenuResponse()) {
      handleMenuSelection(selection);
      displayMainMenu();
      selection = getMenuResponse();
    }
  }

  protected abstract MenuResponseT quitMenuResponse();

  protected abstract void handleMenuSelection(MenuResponseT selection);

  protected abstract MenuResponseT getMenuResponse();

  protected abstract void displayMainMenu();
}
