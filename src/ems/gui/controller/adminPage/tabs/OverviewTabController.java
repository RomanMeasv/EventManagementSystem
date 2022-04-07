package ems.gui.controller.adminPage.tabs;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewTabController implements Initializable {
    public ListView<Event> ltvEvents;
    public TextField txfFilterEvents;
    public ListView<EventCoordinator> ltvCoordinators;
    public TextField txfFilterCoordinators;

    private ModelFacade facade;
    private EventCoordinatorModel eventCoordinatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        ltvEvents.setItems(facade.getAllEvents());
        ltvCoordinators.setItems(eventCoordinatorModel.getAllEventCoordinators());

    }


    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            FilteredList<Event> filteredEvents = facade.getFilteredEvents(query);
            ltvEvents.setItems(filteredEvents);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterCoordinators(KeyEvent keyEvent) {
        try {
            String query = txfFilterCoordinators.getText();
            FilteredList<EventCoordinator> filteredECs = eventCoordinatorModel.filterEventCoordinators(query);
            ltvCoordinators.setItems(filteredECs);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }
}
