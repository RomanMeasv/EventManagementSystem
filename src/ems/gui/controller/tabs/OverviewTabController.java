package ems.gui.controller.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import ems.gui.model.CustomerModel;
import ems.gui.model.EventModel;
import ems.gui.model.TicketModel;
import ems.gui.view.util.PopUp;
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

    private EventModel eventModel;
    private CustomerModel customerModel;
    private TicketModel ticketModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventModel = new EventModel();
            customerModel = new CustomerModel();
            ticketModel = new TicketModel();

            ltvOverviewEvents.setItems(eventModel.getObservableEvents());
            ltvOverviewCustomers.setItems(customerModel.getObservableCustomers());
            ltvOverviewTickets.setItems(ticketModel.getObservableTickets());
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try{
            String query = txfFilterEvents.getText();
            eventModel.filterEvents(query);
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = txfFilterCustomers.getText();
            customerModel.filterCustomers(query);
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterTickets(KeyEvent keyEvent) {
        try{
            String query = txfFilterTickets.getText();
            ticketModel.filterTickets(query);
        } catch (Exception e){
            PopUp.showError(e.getMessage());
        }
    }
}
