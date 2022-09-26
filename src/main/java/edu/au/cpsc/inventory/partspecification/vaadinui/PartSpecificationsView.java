package edu.au.cpsc.inventory.partspecification.vaadinui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification.PartSpecificationModel;
import java.util.Optional;

/**
 * View that displays a grid of part specifications and an editor to edit them.
 */
@Route(value = "/part-specifications", layout = MainLayout.class)
public class PartSpecificationsView extends HorizontalLayout {

  private static final long serialVersionUID = 0L;

  private final Grid<PartSpecificationModel> grid;
  private final PartSpecificationEditorView editorForm;
  private transient CreatePartSpecification createPartSpecification;

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
    updateGrid();
  }

  private Grid<PartSpecificationModel> createGrid() {
    Grid<PartSpecificationModel> grid = new Grid<>(
        PartSpecificationModel.class, true);
    grid.setSelectionMode(SelectionMode.SINGLE);
    grid.addSelectionListener(event -> gridSelectionChanged(event));
    return grid;
  }

  private void gridSelectionChanged(
      SelectionEvent<Grid<PartSpecificationModel>, PartSpecificationModel> event) {
    Optional<PartSpecificationModel> selected = event.getFirstSelectedItem();
    editorForm.showPartSpecification(selected.orElse(new PartSpecificationModel()));
  }

  private void gridItemClicked(ItemClickEvent<PartSpecificationModel> event) {
    PartSpecificationModel selected = event.getItem();
    editorForm.showPartSpecification(selected);
  }

  private void updateGrid() {
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
    PartSpecificationModel newModel = new PartSpecificationModel();
    editorForm.showPartSpecification(newModel);
    grid.asSingleSelect().clear();
  }

  private PartSpecificationEditorView createEditor() {
    PartSpecificationEditorView editor = new PartSpecificationEditorView();
    editor.addSaveListener(event -> saveButtonClicked());
    editor.addCancelListener(event -> cancelButtonClicked());
    return editor;
  }

  private void saveButtonClicked() {
    PartSpecificationModel editedModel = new PartSpecificationModel();
    try {
      editorForm.updatePartSpecification(editedModel);
      if (editedModel.getId() == null) {
        createPartSpecification.createPartSpecification(editedModel);
      } else {
        Notification.show("Did not implement edit use case");
      }
      updateGrid();
    } catch (ValidationException e) {
      Notification.show("Validation failed");
    }
  }

  private void cancelButtonClicked() {
    editorForm.showPartSpecification(new PartSpecificationModel());
  }

}
