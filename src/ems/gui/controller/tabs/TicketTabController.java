package ems.gui.controller.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketTabController implements Initializable {

    public ListView<Ticket> ltvTickets;
    public TextField txfFilterTickets;

    public TextField txfFilterEvents;
    public ComboBox<Event> cmbEvents;

    public TextField txfFilterTicketTypes;
    public ComboBox<String> cmbTicketTypes;

    public TextField txfFilterCustomers;
    public ComboBox<Customer> cmbCustomers;

    public TextField txfNoTickets;
    public Label lblTicketUUID;
    public CheckBox chbTicketValidation;

    public Button btnCancelTicket;
    public Button btnApplyTicket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ltvTickets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedTicket) -> {
            selectedTicketListener(selectedTicket);
        });
    }

    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleNewTicket(ActionEvent event) {

    }

    public void handleRemoveTicket(ActionEvent event) {

    }

    public void handleFilterEventComboBox(KeyEvent keyEvent) {

    }

    public void handleEventForTicketSelected(ActionEvent event) {

    }

    public void handleFilterTicketTypeComboBox(KeyEvent keyEvent) {

    }

    public void handleFilterCustomerComboBox(KeyEvent keyEvent) {

    }

    public void handleCancelTicket(ActionEvent event) {

    }

    public void handleApplyTicket(ActionEvent event) {

    }

    private void selectedTicketListener(Ticket selectedTicket) {

    }
}
