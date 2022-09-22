package edu.au.cpsc.inventory.partspecification.vaadinui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * View that displays a grid of Suppliers and an editor to edit them.
 */
@Route(value = "/suppliers", layout = MainLayout.class)
public class SuppliersView extends VerticalLayout {

  private static final long serialVersionUID = 0L;

  public SuppliersView() {
    add("Suppliers");
  }

}
