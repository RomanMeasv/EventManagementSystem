package ems.gui.controller;

import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.EventDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class EventCoordinatorPageControllerNew implements Initializable {
    private EventModel eventModel;

    public TableView<Event> tbvEvents;
    public TableColumn<Event, String> colEvents;
    public TextField txfFilterEvents;
    public VBox boxEvents;
    public HBox boxEventsButtons;

    public TableView tbvCustomers;
    public TableColumn colCustomers;
    public TextField txfFilterCustomers;
    public VBox boxCustomers;
    public HBox boxCustomersButtons;

    public TableView tbvTickets;
    public TableColumn colTickets;
    public TextField txfFilterTickets;
    public VBox boxTickets;
    public HBox boxTicketsButtons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boxEventsButtons.managedProperty().bind(boxEventsButtons.visibleProperty());
        boxCustomersButtons.managedProperty().bind(boxCustomersButtons.visibleProperty());
        boxTicketsButtons.managedProperty().bind(boxTicketsButtons.visibleProperty());

        //Event set up
        colEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEvents.setItems(eventModel.getObservableEvents());
    }

    /* EVENTS */
    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            eventModel.filterEvents(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCreateEvent(MouseEvent mouseEvent) {
        EventDialog dialog = new EventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                eventModel.createEvent(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void handleDeleteEvent(MouseEvent mouseEvent) {
        try {
            Event selected = tbvEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateEvent(MouseEvent mouseEvent) {
        try {
            Event oldEvent = tbvEvents.getSelectionModel().getSelectedItem();
            if (oldEvent != null) {
                EventDialog dialog = new EventDialog();
                dialog.setFields(oldEvent);
                Optional<Event> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Event updatedEvent = result.get();
                    updatedEvent.setId(oldEvent.getId());
                    eventModel.updateEvent(oldEvent, updatedEvent);
                }
            }
        } catch (Exception e) {
            //don't do anything
        }
    }

    /* CUSTOMERS */
    public void handleFilterCustomers(KeyEvent keyEvent) {

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

    /* TAB CHANGING */
    public void selectionChangedOverviewTab(Event event) {

    }

    public void selectionChangedEventTab(Event event) {

    }

    public void selectionChangedCustomerTab(Event event) {

    }

    public void selectionChangedTicketTab(Event event) {

    }

    public void selectionChangedOverviewTab(javafx.event.Event event) {
    }

    public void selectionChangedEventTab(javafx.event.Event event) {
    }

    public void selectionChangedCustomerTab(javafx.event.Event event) {
    }

    public void selectionChangedTicketTab(javafx.event.Event event) {
    }
}
