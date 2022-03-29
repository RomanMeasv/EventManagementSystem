package ems.gui.controller;

import ems.be.Customer;
import ems.gui.model.CustomerModel;
import ems.gui.view.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.EventDialog;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {

    /* OVERVIEW TAB */
        /* EVENTS */
    public TableView<Event> tbvOverviewEvents;
    public TableColumn<Event, String> colOverviewEvents;
    public TextField txfFilterOverviewEvents;

        /* CUSTOMERS */
    public TableView<Customer> tbvOverviewCustomers;
    public TableColumn<Customer, String> colOverviewCustomers;
    public TextField txfFilterOverviewCustomers;

        /* TICKETS */
    public TableView tbvOverviewTickets;
    public TableColumn colOverviewTickets;
    public TextField txfFilterOverviewTickets;

    /* EVENTS TAB */
        /* TABLE VIEW */
    public TableView<Event> tbvEventTabEvents;
    public TableColumn<Event, String> colEventTabEvents;
    public TextField txfFilterEventTabEvents;

        /* DIALOG PANE */
    public TextField txfEventName, txfEventDescription, txfEventNotes,
                txfStartDate, txfStartTime,
                txfEndDate, txfEndTime,
                txfLocation, txfLocationGuidance;
    public ListView<String> ltvTicketTypes;
    public TextField txfTicketType;

    /* MODELS */
    private CustomerModel customerModel;
    private EventModel eventModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerModel = new CustomerModel();
            eventModel = new EventModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage()); //error is custom handled within the logic
        }

        /* SET UP OVERVIEW TAB */
        colOverviewEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvOverviewEvents.setItems(eventModel.getObservableEvents());
        colOverviewCustomers.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvOverviewCustomers.setItems(customerModel.getObservableCustomers());

        /* SET UP EVENTS TAB */
        colEventTabEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEventTabEvents.setItems(eventModel.getObservableEvents());
    }

    /* EVENTS */
    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = ((TextField)keyEvent.getSource()).getText();
            eventModel.filterEvents(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCreateEvent(MouseEvent mouseEvent) {

        /*EventDialog dialog = new EventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                eventModel.createEvent(response);
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        });*/
    }

    public void handleDeleteEvent(MouseEvent mouseEvent) {
        try {
            Event selected = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleUpdateEvent(MouseEvent mouseEvent) {
        try {
            Event oldEvent = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            /*if (oldEvent != null) {
                EventDialog dialog = new EventDialog();
                dialog.setFields(oldEvent);
                Optional<Event> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Event updatedEvent = result.get();
                    updatedEvent.setId(oldEvent.getId());
                    eventModel.updateEvent(oldEvent, updatedEvent);
                }
            }*/
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    /* CUSTOMERS */
    public void handleFilterCustomers(KeyEvent keyEvent) {
        //txfFilterOverviewCustomers
    }

    public void handleCreateCustomer(MouseEvent mouseEvent) {

    }

    public void handleDeleteCustomer(MouseEvent mouseEvent) {

    }

    public void handleUpdateCustomer(MouseEvent mouseEvent) {

    }

    /* TICKETS */
    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleCreateTicket(MouseEvent mouseEvent) {

    }

    public void handleDeleteTicket(MouseEvent mouseEvent) {

    }

    public void handleUpdateTicket(MouseEvent mouseEvent) {

    }

    public void startDateKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void startTimeKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void endDateKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void endTimeKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void handleAddTicketType(ActionEvent event) {

    }

    public void handleRemoveTicketType(ActionEvent event) {

    }
}
