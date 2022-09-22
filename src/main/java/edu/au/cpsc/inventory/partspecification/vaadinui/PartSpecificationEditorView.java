package edu.au.cpsc.inventory.partspecification.vaadinui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import edu.au.cpsc.inventory.partspecification.usecase.CreatePartSpecification.PartSpecificationModel;

/**
 * Editor that edits PartSpecification (Model) instances.
 */
public class PartSpecificationEditorView extends VerticalLayout {

  private final Binder<PartSpecificationModel> binder;

  /**
   * Create and populate this view.
   */
  public PartSpecificationEditorView() {
    binder = new Binder<>();
    FormLayout form = new FormLayout();
    TextField nameField = new TextField("Name");
    binder.bind(nameField, m -> m.getName(), (m, v) -> m.setName(v));
    TextArea descriptionField = new TextArea("Description");
    binder.bind(descriptionField, m -> m.getDescription(), (m, v) -> m.setDescription(v));
    form.add(nameField, descriptionField);
    HorizontalLayout buttonBar = new HorizontalLayout();
    Button saveButton = new Button("Save");
    saveButton.addClickListener(event -> saveButtonClicked());
    Button cancelButton = new Button("Cancel");
    cancelButton.addClickListener(event -> cancelButtonClicked());
    buttonBar.add(saveButton, cancelButton);
    add(form, buttonBar);
  }

  public void showPartSpecification(PartSpecificationModel m) {
    binder.readBean(m);
  }

  public void updatePartSpecification(PartSpecificationModel m) throws ValidationException {
    binder.writeBean(m);
  }

  public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
    return addListener(SaveEvent.class, listener);
  }

  public Registration addCancelListener(ComponentEventListener<CancelEvent> listener) {
    return addListener(CancelEvent.class, listener);
  }

  private void cancelButtonClicked() {
    fireEvent(new CancelEvent(this, false));
  }

  private void saveButtonClicked() {
    fireEvent(new SaveEvent(this, false));
  }

  private static class SaveEvent extends ComponentEvent<PartSpecificationEditorView> {

    public SaveEvent(PartSpecificationEditorView source, boolean fromClient) {
      super(source, fromClient);
    }
  }

  private static class CancelEvent extends ComponentEvent<PartSpecificationEditorView> {

    public CancelEvent(PartSpecificationEditorView source, boolean fromClient) {
      super(source, fromClient);
    }
  }

}
