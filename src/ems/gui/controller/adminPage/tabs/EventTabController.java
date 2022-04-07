package ems.gui.controller.adminPage.tabs;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.gui.model.EventCoordinatorModel;
import ems.gui.model.ModelFacade;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EventTabController implements Initializable {
    public ListView <Event>ltvEvents;
    public TextField txfFilterEvents;
    public TextField txfEventName;
    public TextField txfEventDescription;
    public ListView<EventCoordinator> ltvEventsCoordinators;
    public TextField txfFilterEventsCoordinators;
    public ComboBox cmbCoordinators;
    public Button btnCancelEvent;
    public Button btnApplyEvent;

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

        ltvEvents.setItems(facade.getAllEvents());
        cmbCoordinators.setItems(eventCoordinatorModel.getAllEventCoordinators());

        ltvEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedEvent) -> {
            if (selectedEvent != null) {
                selectedEventListener(selectedEvent);}});

    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        String query = txfFilterEvents.getText();
        try {
            ltvEvents.setItems(facade.getFilteredEvents(query));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleRemoveEvent(ActionEvent event) {
        Event selectedEvent = ltvEvents.getSelectionModel().getSelectedItem();
        if(selectedEvent != null){
            try {
                facade.deleteEvent(selectedEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleFilterCoordinators(KeyEvent keyEvent) {
        String query = txfFilterEventsCoordinators.getText();
        if(!query.isEmpty()){
        try {
            cmbCoordinators.setItems(eventCoordinatorModel.filterEventCoordinators(query));
            cmbCoordinators.hide();
            cmbCoordinators.setVisibleRowCount(5);
            cmbCoordinators.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        else{
            cmbCoordinators.hide();
        }

    }

    public void handleAddCoordinator(ActionEvent event) {
        //adds a coordinator from combobox into events list of via versa
    }

    public void handleRemoveCoordinator(ActionEvent event) {
        //removes a selected coordinator from listview from event list or what ever architecture we will go with

    }

    public void handleCancelEvent(ActionEvent event) {
    }

    public void handleApplyEvent(ActionEvent event) {
    }

    private void selectedEventListener(Event event){
        fillEventDetails(event);
    }

    private void setDisableButtons(boolean state){
        btnApplyEvent.setDisable(state);
        btnCancelEvent.setDisable(state);
    }

    private void fillEventDetails(Event event){
        txfEventName.setText(event.getName());
        txfEventDescription.setText(event.getDescription());
    }

    private void cleatEventDetails(){
        txfEventName.clear();
        txfEventDescription.clear();
    }
}
