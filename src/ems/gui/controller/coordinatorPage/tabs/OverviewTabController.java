package ems.gui.controller.coordinatorPage.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewTabController implements Initializable {

    public ListView<Event> ltvOverviewEvents;
    public ListView<Customer> ltvOverviewCustomers;
    public ListView<Ticket> ltvOverviewTickets;

    public TextField txfFilterEvents;
    public TextField txfFilterCustomers;
    public TextField txfFilterTickets;

    private ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
            ltvOverviewEvents.setItems(facade.getAllEvents());
            ltvOverviewCustomers.setItems(facade.getAllCustomers());
            ltvOverviewTickets.setItems(facade.getAllTickets());
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        ltvOverviewEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ltvOverviewCustomers.setItems(FXCollections.observableList(facade.getAllTickets().stream().filter(ticket -> ticket.getEvent().equals(newValue)).map(Ticket::getCustomer).distinct().collect(java.util.stream.Collectors.toList())));
            ltvOverviewTickets.setItems(FXCollections.observableList(facade.getAllTickets().stream().filter(ticket -> ticket.getEvent().equals(newValue)).collect(java.util.stream.Collectors.toList())));
        });
        ltvOverviewCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ltvOverviewTickets.setItems(FXCollections.observableList(facade.getAllTickets().stream().filter(ticket -> ticket.getCustomer().equals(newValue)).collect(java.util.stream.Collectors.toList())));
        });
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            FilteredList<Event> filteredEvents = facade.getFilteredEvents(query);
            ltvOverviewEvents.setItems(filteredEvents);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = txfFilterCustomers.getText();
            FilteredList<Customer> filteredCustomers = facade.getFilteredCustomers(query);
            ltvOverviewCustomers.setItems(filteredCustomers);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterTickets(KeyEvent keyEvent) {
        try {
            String query = txfFilterTickets.getText();
            FilteredList<Ticket> filteredTickets = facade.getFilteredTickets(query);
            ltvOverviewTickets.setItems(filteredTickets);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }
}
