package ems.gui.controller;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {

    @FXML
    private TableView<Event> tvEvents;
    @FXML
    private TableView<Ticket> tvTickets;
    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TableColumn<Event, String> tvColEvent;
    @FXML
    private TableColumn<Ticket, String> tvColTicket;
    @FXML
    private TableColumn<Customer, String> tvColCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvColEvent.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        tvColTicket.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
        tvColCustomer.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));

        ObservableList<Event> eventList = FXCollections.observableArrayList();
        ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        for (int i = 0; i < 100; i++) {
            Event e = new Event("event"+i);
            Ticket t = new Ticket("ticket"+i);
            Customer c = new Customer("customer"+i);

            eventList.add(e);
            ticketList.add(t);
            customerList.add(c);
        }

        tvEvents.setItems(eventList);
        tvTickets.setItems(ticketList);
        tvCustomers.setItems(customerList);

    }

    public void handleAddEvent(MouseEvent mouseEvent) {
    }
}
