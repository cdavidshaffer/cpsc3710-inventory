package edu.au.cpsc.inventory.partspecification.vaadinui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification.PartSpecificationModel;

/**
 * View that displays a grid of part specifications and an editor to edit them.
 */
@Route(value = "/part-specifications", layout = MainLayout.class)
public class PartSpecificationsView extends HorizontalLayout {

  private final Grid grid;
  private final PartSpecificationEditorView editorForm;
  private CreatePartSpecification createPartSpecification;

  /**
   * Create this view and all of its sub-components.
   *
   * @param createPartSpecification our use case
   */
  public PartSpecificationsView(CreatePartSpecification createPartSpecification) {
    this.createPartSpecification = createPartSpecification;
    VerticalLayout leftSection = new VerticalLayout();
    grid = createGrid();
    leftSection.add(createToolbar(), grid);
    editorForm = createEditor();
    add(leftSection, editorForm);
    populateGrid();
  }

  private Grid<PartSpecificationModel> createGrid() {
    Grid<PartSpecificationModel> grid = new Grid<>(
        PartSpecificationModel.class, true);
    grid.addItemClickListener(event -> gridItemClicked(event));
    return grid;
  }

  private void gridItemClicked(ItemClickEvent<PartSpecificationModel> event) {
    PartSpecificationModel selected = event.getItem();
    editorForm.showPartSpecification(selected);
  }

  private void populateGrid() {
    grid.setItems(createPartSpecification.getPartSpecifications());
  }

  private Component createToolbar() {
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    Button addButton = new Button("Add");
    addButton.addClickListener(event -> addButtonPressed());
    horizontalLayout.add(addButton);
    return horizontalLayout;
  }

  private void addButtonPressed() {
    Notification.show("Add pressed");
  }

  private PartSpecificationEditorView createEditor() {
    return new PartSpecificationEditorView();
  }

}
