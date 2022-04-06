package ems.gui.controller.adminTabPane;

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

public class AdminOverviewTapController implements Initializable {
    public ListView <Event> ltvOverviewEvents;
    public TextField txfFilterEvents;
    public ListView <EventCoordinator> ltvOverviewECs;
    public TextField txfFilterECs;

    private ModelFacade facade;
    private EventCoordinatorModel eventCoordinatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
            eventCoordinatorModel = new EventCoordinatorModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ltvOverviewEvents.setItems(facade.getAllEvents());
        ltvOverviewECs.setItems(eventCoordinatorModel.getAllEventCoordinators());

    }


    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            FilteredList<Event> filteredEvents = facade.getFilteredEvents(query);
            ltvOverviewEvents.setItems(filteredEvents);
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterECs(KeyEvent keyEvent) {
        try{
            String query = txfFilterECs.getText();
            FilteredList<EventCoordinator> filteredECs = eventCoordinatorModel.filterEventCoordinators(query);
            ltvOverviewECs.setItems(filteredECs);
        }catch(Exception e){
            PopUp.showError(e.getMessage());
        }
    }
}
