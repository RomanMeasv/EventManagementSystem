package ems.gui.controller;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import ems.gui.model.CustomerModel;
import ems.gui.model.EventModel;
import ems.gui.model.TicketModel;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {

    /* TICKETS TAB */
    /* TABLE VIEW */
    public TableView<Ticket> tbvTicketTabTickets;
    public TableColumn<Ticket, String> colTicketTabTickets;
    public TextField txfFilterTickets;

    /* "DIALOG PANE" */
    public TextField txfTicketTabFilterEvents,
            txfTicketTabFilterTicketType,
            txfTicketTabFilterCustomers;
    public ComboBox<Event> cmbEvents;
    public ComboBox<String> cmbTicketTypes;
    public ComboBox<Customer> cmbCustomers;
    public TextField txfNoTickets;
    public Label lblTicketUUID;
    public CheckBox chbTicketValidation;
    public Button btnCancelTicket;
    public Button btnApplyTicket;


    /* MODELS */
    private EventModel eventModel;
    private CustomerModel customerModel;
    private TicketModel ticketModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            eventModel = new EventModel();
            customerModel = new CustomerModel();
            ticketModel = new TicketModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage()); //error is custom handled within the logic
        }

        /* Disable cancel/apply buttons */
        /*btnApplyEvent.setDisable(true);
        btnCancelEvent.setDisable(true);
        btnApplyCustomer.setDisable(true);
        btnCancelCustomer.setDisable(true);
        btnApplyTicket.setDisable(true);
        btnCancelTicket.setDisable(true);*/

        //hide ticket creation/edit specific fields
        /*lblTicketUUID.setVisible(false);
        txfNoTickets.setVisible(false);
        chbTicketValidation.setVisible(false);*/
    }

    /*private void refreshTickets()
    {
        try{
            ticketModel.clearFilter();
            tbvOverviewTickets.getItems().clear();
            tbvOverviewTickets.getItems().addAll(ticketModel.getObservableTickets());
            tbvTicketTabTickets.getItems().clear();
            tbvTicketTabTickets.getItems().addAll(ticketModel.getObservableTickets());
        } catch (Exception ex){
            PopUp.showError(ex.getMessage());
        }
    }*/

    //region TICKET TAB
    public void handleSelectTicket(MouseEvent mouseEvent) {
        clearTicketComboBoxFilters();

        Ticket t = tbvTicketTabTickets.getSelectionModel().getSelectedItem();
        if(t != null){
            fillTicketDetails(t);
        }


        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);

        txfNoTickets.setVisible(false);

        lblTicketUUID.setVisible(true);
        chbTicketValidation.setVisible(true);
    }

    private void clearTicketComboBoxFilters()
    {
        txfTicketTabFilterEvents.clear();
        txfTicketTabFilterTicketType.clear();
        txfTicketTabFilterCustomers.clear();
    }

    private void fillTicketDetails(Ticket t) {
        //fill up ComboBoxes
        cmbEvents.setItems(FXCollections.observableArrayList(t.getEvent()));
        cmbTicketTypes.setItems(FXCollections.observableArrayList(t.getEvent().getTicketTypes()));
        cmbCustomers.setItems(FXCollections.observableArrayList(t.getCustomer()));

        cmbEvents.getSelectionModel().select(0);
        cmbTicketTypes.getSelectionModel().select(t.getEvent().getTicketTypes().indexOf(t.getTicketType()));
        cmbCustomers.getSelectionModel().select(0);

        lblTicketUUID.setText(t.getUuid().toString());
        chbTicketValidation.setSelected(t.isValid());
    }

    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleNewTicket(ActionEvent actionEvent) {
        //enable buttons
        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);

        tbvTicketTabTickets.getSelectionModel().clearSelection();
        clearTicketComboBoxFilters();
        clearTicketDetails();
        setUpFieldsForNewTicket();
    }

    private void setUpFieldsForNewTicket() {
        cmbEvents.setItems(eventModel.getObservableEvents());
        cmbTicketTypes.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        cmbCustomers.setItems(customerModel.getObservableCustomers());

        //set hide ticketValidation (is not taken into account when constructing a new ticket)
        chbTicketValidation.setVisible(false);
        //hide uuid
        lblTicketUUID.setVisible(false);
        //show no of tickets
        txfNoTickets.setVisible(true);
        //default value = 1
        txfNoTickets.setText("1");
    }

    private void clearTicketDetails() {
        cmbEvents.getSelectionModel().clearSelection();
        cmbTicketTypes.getSelectionModel().clearSelection();
        cmbCustomers.getSelectionModel().clearSelection();
    }

    public void handleRemoveTicket(ActionEvent actionEvent) {
        Ticket t = tbvTicketTabTickets.getSelectionModel().getSelectedItem();
        if(t != null){
            try {
                ticketModel.deleteTicket(t);
                tbvTicketTabTickets.getSelectionModel().clearSelection();
                clearTicketDetails();
                clearTicketComboBoxFilters();
                btnApplyTicket.setDisable(true);
                btnCancelTicket.setDisable(true);
            } catch (Exception e){
                PopUp.showError(e.getMessage());
            }
        }
    }

    public void handleFilterEventComboBox(KeyEvent keyEvent) {
        String query = txfTicketTabFilterEvents.getText();
        try {
            if(txfTicketTabFilterEvents.getText().isEmpty()){
                cmbEvents.hide();
            }
            else{
                List<Event> filtered = eventModel.getListOfFiteredEvents(query);
                cmbEvents.setItems(FXCollections.observableArrayList(filtered));
                cmbEvents.hide();
                cmbEvents.setVisibleRowCount(filtered.size());
                cmbEvents.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void handleFilterTicketTypeComboBox(KeyEvent keyEvent) {

    }

    public void handleFilterCustomerComboBox(KeyEvent keyEvent) {
        String query = txfTicketTabFilterCustomers.getText();
        try {
            if(txfTicketTabFilterCustomers.getText().isEmpty()){
                cmbCustomers.hide();
            }
            else{
                List<Customer> filtered = customerModel.getFilteredCustomerList(query);
                cmbCustomers.setItems(FXCollections.observableArrayList(filtered));
                cmbCustomers.hide();
                cmbCustomers.setVisibleRowCount(filtered.size());
                cmbCustomers.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleEventForTicketSelected()
    {
        if (cmbEvents.getSelectionModel().getSelectedItem() != null)
            cmbTicketTypes.setItems(FXCollections.observableArrayList(cmbEvents.getSelectionModel().getSelectedItem().getTicketTypes()));
    }

    public void handleCancelTicket(ActionEvent actionEvent) {
        Ticket t = tbvTicketTabTickets.getSelectionModel().getSelectedItem();
        if (t == null) {
            clearTicketDetails();
            btnCancelTicket.setDisable(true);
            btnApplyTicket.setDisable(true);
        } else {
            fillTicketDetails(t);
        }
        clearTicketComboBoxFilters();
    }

    public void handleApplyTicket(ActionEvent actionEvent) {
        //chech if all necessary fields are filled
        if (cmbEvents.getSelectionModel().getSelectedItem() == null || cmbTicketTypes.getSelectionModel().getSelectedItem() == null || cmbCustomers.getSelectionModel().getSelectedItem() == null) {
            PopUp.showError("Please provide all necessary fields");
            return;
        }

        Ticket t = tbvTicketTabTickets.getSelectionModel().getSelectedItem();
        if(t == null)
        {
            //check if no of tickets is a number
            if (!txfNoTickets.getText().matches("\\d*")) {
                PopUp.showError("Please provide a number for the number of tickets");
                return;
            }
            //check if no of tickets is a number between 1 and 100
            if (Integer.parseInt(txfNoTickets.getText()) < 1 || Integer.parseInt(txfNoTickets.getText()) > 100) {
                PopUp.showError("Please provide a number between 1 and 100!");
                return;
            }

            //create new tickets from fields
            Event event = cmbEvents.getSelectionModel().getSelectedItem();
            String ticketType = cmbTicketTypes.getSelectionModel().getSelectedItem();
            Customer customer = cmbCustomers.getSelectionModel().getSelectedItem();
            try {
                for (int i = 0; i < Integer.parseInt(txfNoTickets.getText()); i++) {
                    ticketModel.createTicket(new Ticket(event, ticketType, customer));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            clearTicketDetails();
            clearTicketComboBoxFilters();
            btnCancelTicket.setDisable(true);
            btnApplyTicket.setDisable(true);
        }
        else //TODO: update the ticket
        {
            t.setValid(chbTicketValidation.isSelected());
            t.setTicketType(cmbTicketTypes.getSelectionModel().getSelectedItem());
            try {
                ticketModel.updateTicket(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearTicketComboBoxFilters();
    }
    //endregion
}
