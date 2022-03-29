package ems.gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {
    public TabPane tbpEventCoordinator;

    public TableView tbvEvents;
    public TableColumn colEvents;
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
        boxEventsButtons.managedProperty().bind(boxEventsButtons.visibleProperty());
        boxCustomersButtons.managedProperty().bind(boxCustomersButtons.visibleProperty());
        boxTicketsButtons.managedProperty().bind(boxTicketsButtons.visibleProperty());
        tbpEventCoordinator.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tabChangeListener(newValue.intValue());
        });
    }

    /* EVENTS */
    public void handleFilterEvents(KeyEvent keyEvent) {

    }

    public void handleCreateEvent(MouseEvent mouseEvent) {

    }

    public void handleDeleteEvent(MouseEvent mouseEvent) {

    }

    public void handleUpdateEvent(MouseEvent mouseEvent) {

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
    public void tabChangeListener(int newValue){
        switch (newValue){
            case 0 -> {
                System.out.println("overview");
            }
            case 1 -> {
                System.out.println("events");
            }
            case 2 -> {
                System.out.println("customers");
            }
            case 3 -> {
                System.out.println("tickets");
            }
        }
    }
}
