package edu.au.cpsc.inventory.partspecification.vaadinui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * Main view for this application.  Serves as the AppLayout for the specific views.
 */
@Route("")
public class MainLayout extends AppLayout {

  /**
   * Create this main view.
   */
  public MainLayout() {
    DrawerToggle toggle = new DrawerToggle();
    addToDrawer(getTabs());
    addToNavbar(toggle, new H3("Cool App"));
  }

  private Tabs getTabs() {
    Tabs tabs = new Tabs();
    tabs.add(
        createTab(VaadinIcon.DASHBOARD, "Part specifications", PartSpecificationsView.class),
        createTab(VaadinIcon.CART, "Suppliers", SuppliersView.class)
    );
    tabs.setOrientation(Tabs.Orientation.VERTICAL);
    return tabs;
  }

  private Tab createTab(VaadinIcon viewIcon, String viewName,
      Class<? extends Component> componentClass) {
    Icon icon = viewIcon.create();
    icon.getStyle()
        .set("box-sizing", "border-box")
        .set("margin-inline-end", "var(--lumo-space-m)")
        .set("margin-inline-start", "var(--lumo-space-xs)")
        .set("padding", "var(--lumo-space-xs)");

    RouterLink link = new RouterLink(componentClass);
    link.add(icon, new Span(viewName));
    link.setTabIndex(-1);

    return new Tab(link);
  }
}