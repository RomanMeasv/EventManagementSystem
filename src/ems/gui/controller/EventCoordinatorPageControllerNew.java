package ems.gui.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {
    public TableView tbvEvents;
    public TableColumn colEvents;
    public TextField txfFilterEvents;
    public HBox boxEventsButtons;

    public TableView tbvCustomers;
    public TableColumn colCustomers;
    public TextField txfFilterCustomers;
    public HBox boxCustomersButtons;

    public TableView tbvTickets;
    public TableColumn colTickets;
    public TextField txfFilterTickets;
    public HBox boxTicketsButtons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boxEventsButtons.managedProperty().bind(boxEventsButtons.visibleProperty());
        boxCustomersButtons.managedProperty().bind(boxCustomersButtons.visibleProperty());
        boxTicketsButtons.managedProperty().bind(boxTicketsButtons.visibleProperty());
    }

    public void handleFilterEvents(KeyEvent keyEvent) {

    }

    public void handleCreateEvent(MouseEvent mouseEvent) {

    }

    public void handleDeleteEvent(MouseEvent mouseEvent) {

    }

    public void handleUpdateEvent(MouseEvent mouseEvent) {

    }

    public void handleFilterCustomers(KeyEvent keyEvent) {

    }

    public void handleCreateCustomer(MouseEvent mouseEvent) {

    }

    public void handleDeleteCustomer(MouseEvent mouseEvent) {

    }

    public void handleUpdateCustomer(MouseEvent mouseEvent) {

    }

    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleCreateTicket(MouseEvent mouseEvent) {

    }

    public void handleDeleteTicket(MouseEvent mouseEvent) {

    }

    public void handleUpdateTicket(MouseEvent mouseEvent) {

    }
}
