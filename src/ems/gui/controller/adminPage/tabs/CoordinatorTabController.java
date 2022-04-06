package ems.gui.controller.adminPage.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CoordinatorTabController implements Initializable {
    public ListView ltvTickets;
    public TextField txfFilterTickets;
    public TextField txfFilterEvents;
    public ListView ltvEventTicketTypes;
    public TextField txfEventTicketType;
    public Button btnCancelTicket;
    public Button btnApplyTicket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleFilterEvents(KeyEvent keyEvent) {
    }

    public void handleFilterTickets(KeyEvent keyEvent) {
    }

    public void handleNewTicket(ActionEvent event) {
    }

    public void handleRemoveTicket(ActionEvent event) {
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
    }

    public void handleAddTicketType(ActionEvent event) {
    }

    public void handleRemoveTicketType(ActionEvent event) {
    }

    public void handleCancelTicket(ActionEvent event) {
    }

    public void handleApplyTicket(ActionEvent event) {
    }
}
