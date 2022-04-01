package ems.gui.controller;

import ems.be.Customer;
import ems.be.Ticket;
import ems.gui.model.CustomerModel;
import ems.gui.model.TicketModel;
import ems.gui.view.util.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import ems.be.Event;
import ems.gui.model.EventModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {
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
    public TableView<Ticket> tbvOverviewTickets;
    public TableColumn<Ticket, String> colOverviewTickets;
    public TextField txfFilterOverviewTickets;


    /* EVENTS TAB */
    /* TABLE VIEW */
    public TableView<Event> tbvEventTabEvents;
    public TableColumn<Event, String> colEventTabEvents;
    public TextField txfFilterEventTabEvents;

    /* "DIALOG PANE" */
    public TextField txfEventName,
            txfEventStartDate, txfEventStartTime,
            txfEventEndDate, txfEventEndTime;
    public TextArea txaEventDescription, txaEventNotes, txaEventLocation, txaEventLocationGuidance;
    public ListView<String> ltvEventTicketTypes;
    public TextField txfEventTicketType;
    public Button btnApplyEvent, btnCancelEvent;


    /* CUSTOMERS TAB */
    /* TABLE VIEW */
    public TableView<Customer> tbvCustomerTabCustomers;
    public TableColumn<Customer, String> colCustomerTabCustomers;
    public TextField txfFilterCustomers;

    /* "DIALOG PANE" */
    public TextField txfCustomerName, txfCustomerEmail,
            txfCustomerPhoneNumber;
    public TextArea txaCustomerNotes;
    public ListView<Event> ltvCustomerAttendingEvents;
    public TextField txfFilterAttendingEvents;
    public Button btnApplyCustomer, btnCancelCustomer;


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
    private CustomerModel customerModel;
    private EventModel eventModel;
    private TicketModel ticketModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerModel = new CustomerModel();
            eventModel = new EventModel();
            ticketModel = new TicketModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage()); //error is custom handled within the logic
        }

        /* SET UP OVERVIEW TAB */
        //set col overview events cell value factory to simple string property
        colOverviewEvents.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().toString()));
        tbvOverviewEvents.setItems(eventModel.getObservableEvents());
        colOverviewCustomers.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().toString()));
        tbvOverviewCustomers.setItems(customerModel.getObservableCustomers());
        colOverviewTickets.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().toString()));
        tbvOverviewTickets.setItems(ticketModel.getObservableTickets());

        /* SET UP EVENTS TAB */
        colEventTabEvents.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().toString()));
        tbvEventTabEvents.setItems(eventModel.getObservableEvents());

        /* SET UP CUSTOMERS TAB */
        colCustomerTabCustomers.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().toString()));
        tbvCustomerTabCustomers.setItems(customerModel.getObservableCustomers());

        /* SET UP TICKETS TAB */
        colTicketTabTickets.setCellValueFactory(t -> new SimpleStringProperty(t.getValue().toString()));
        tbvTicketTabTickets.setItems(ticketModel.getObservableTickets());


        /* Disable cancel/apply buttons */
        btnApplyEvent.setDisable(true);
        btnCancelEvent.setDisable(true);
        btnApplyCustomer.setDisable(true);
        btnCancelCustomer.setDisable(true);

        /*Fill comboBoxes with data*/
        cmbEvents.setItems((ObservableList<Event>) eventModel.getObservableEvents());
        cmbCustomers.setItems((ObservableList<Customer>) customerModel.getObservableCustomers());

    }

    // region EVENTS TAB
    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = ((TextField) keyEvent.getSource()).getText();
            eventModel.filterEvents(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleNewEvent(ActionEvent actionEvent) {
        btnApplyEvent.setDisable(false);
        btnCancelEvent.setDisable(false);

        tbvEventTabEvents.getSelectionModel().clearSelection();

        clearEventDetails();
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
    }

    public void handleRemoveEvent() {
        try {
            Event selected = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
            btnApplyEvent.setDisable(true);
            btnCancelEvent.setDisable(true);
            clearEventDetails();
            tbvEventTabEvents.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleApplyEvent() {
        Event e = tbvEventTabEvents.getSelectionModel().getSelectedItem();
        //check if everything is correct
        if (txfEventName.getText().isEmpty() || txfEventStartDate.getText().isEmpty() || txfEventStartTime.getText().isEmpty() ||
                txfEventEndDate.getText().isEmpty() || txfEventEndTime.getText().isEmpty() || txaEventLocation.getText().isEmpty() || ltvEventTicketTypes.getItems().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields and add at least one ticket type! (*)");
            return;
        }

        if (e != null) {
            if (eventModel.getObservableEvents().stream().map(Event::getName).toList().contains(txfEventName.getText())
                    && !txfEventName.getText().equals(e.getName())) {
                PopUp.showError("Event name already in use!");
                return;
            }
        } else {
            if (eventModel.getObservableEvents().stream().map(Event::getName).toList().contains(txfEventName.getText())) {
                PopUp.showError("Event name already in use!");
                return;
            }
        }

        //check if dates can be parsed (if they are valid)
        LocalDateTime start;
        LocalDateTime end;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            start = LocalDateTime.parse(txfEventStartDate.getText() + " " + txfEventStartTime.getText(), formatter);
            end = LocalDateTime.parse(txfEventEndDate.getText() + " " + txfEventEndTime.getText(), formatter);
            if (start.isAfter(end)) {
                PopUp.showError("Start date cannot be after end date!");
                return;
            }
        } catch (Exception exception) {
            PopUp.showError("Invalid date format!");
            return;
        }

        if (e == null) { //it's a new event
            try {
                eventModel.createEvent(new Event(txfEventName.getText(), txaEventDescription.getText(), txaEventNotes.getText(), start, end, txaEventLocation.getText(), txaEventLocationGuidance.getText(), ltvEventTicketTypes.getItems()));
                btnApplyEvent.setDisable(true);
                btnCancelEvent.setDisable(true);
                clearEventDetails();
            } catch (Exception ex) {
                PopUp.showError(ex.getMessage());
            }
        } else //it's an existing event
        {
            try {
                e.setName(txfEventName.getText());
                e.setDescription(txaEventDescription.getText());
                e.setNotes(txaEventNotes.getText());
                e.setStart(start);
                e.setEnd(end);
                e.setLocation(txaEventLocation.getText());
                e.setLocationGuidance(txaEventLocationGuidance.getText());
                e.setTicketTypes(ltvEventTicketTypes.getItems());
                eventModel.updateEvent(e);
                ticketModel.clearFilter();
                ticketModel.getObservableTickets().stream().filter(t -> t.getEvent().equals(e)).forEach(t -> t.setEvent(e));

            } catch (Exception ex) {
                PopUp.showError(ex.getMessage());
            }
        }

        //refresh ticket data
        try{
            ticketModel.readAllTickets();
            tbvOverviewTickets.setItems(ticketModel.getObservableTickets());
            tbvTicketTabTickets.setItems(ticketModel.getObservableTickets());
        } catch (Exception ex){
            PopUp.showError(ex.getMessage());
        }
    }

    public void handleAddTicketType(ActionEvent event) {
        if (txfEventTicketType.getText().isEmpty()) {
            return;
        }

        if (ltvEventTicketTypes.getItems().contains(txfEventTicketType.getText())) {
            txfEventTicketType.clear();
            return;
        }

        ltvEventTicketTypes.getItems().add(txfEventTicketType.getText());
        txfEventTicketType.clear();
    }

    public void handleRemoveTicketType(ActionEvent event) {
        ltvEventTicketTypes.getItems().remove(ltvEventTicketTypes.getSelectionModel().getSelectedItem());
    }

    public void handleCancelEvent() {
        Event e = tbvEventTabEvents.getSelectionModel().getSelectedItem();
        if (e == null) { //a new event was about to be created
            clearEventDetails();
            btnApplyEvent.setDisable(true);
            btnCancelEvent.setDisable(true);
        } else //an existing event was about to be edited
        {
            fillEventDetails(e);
        }
    }

    public void handleSelectEvent() {
        Event e = tbvEventTabEvents.getSelectionModel().getSelectedItem();
        if (e != null) {
            fillEventDetails(e);
        }
        btnApplyEvent.setDisable(false);
        btnCancelEvent.setDisable(false);
    }

    private void clearEventDetails() {
        txfEventName.clear();
        txaEventDescription.clear();
        txaEventNotes.clear();
        txfEventStartDate.clear();
        txfEventStartTime.clear();
        txfEventEndDate.clear();
        txfEventEndTime.clear();
        txaEventLocation.clear();
        txaEventLocationGuidance.clear();
        ltvEventTicketTypes.getItems().clear();
        txfEventTicketType.clear();
    }

    private void fillEventDetails(Event e) {
        txfEventName.setText(e.getName());
        txaEventDescription.setText(e.getDescription());
        txaEventNotes.setText(e.getNotes());
        txfEventStartTime.setText(e.getStart().toLocalTime().toString());
        txfEventStartDate.setText(e.getStart().toLocalDate().toString());
        txfEventEndTime.setText(e.getEnd().toLocalTime().toString());
        txfEventEndDate.setText(e.getEnd().toLocalDate().toString());
        txaEventLocation.setText(e.getLocation());
        txaEventLocationGuidance.setText(e.getLocationGuidance());
        ltvEventTicketTypes.setItems(FXCollections.observableList(new ArrayList<>(e.getTicketTypes())));
    }


    //endregion

    // region CUSTOMER TAB
    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = ((TextField) keyEvent.getSource()).getText();
            customerModel.filterCustomers(query);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleSelectCustomer(MouseEvent mouseEvent) {
        Customer c = tbvCustomerTabCustomers.getSelectionModel().getSelectedItem();
        if (c != null) {
            fillCustomerDetails(c);
        }
        btnApplyCustomer.setDisable(false);
        btnCancelCustomer.setDisable(false);
    }

    public void handleNewCustomer(ActionEvent event) {
        btnApplyCustomer.setDisable(false);
        btnCancelCustomer.setDisable(false);
        tbvCustomerTabCustomers.getSelectionModel().clearSelection();
        clearCustomerDetails();
    }

    public void handleRemoveCustomer(ActionEvent event) {
        try {
            Customer selected = tbvCustomerTabCustomers.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customerModel.deleteCustomer(selected);
            }
            btnApplyCustomer.setDisable(true);
            btnCancelCustomer.setDisable(true);
            clearCustomerDetails();
            tbvCustomerTabCustomers.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterAttendingEvents(KeyEvent keyEvent) {
    }

    public void handleApplyCustomer(ActionEvent event) {
        Customer c = tbvCustomerTabCustomers.getSelectionModel().getSelectedItem();
        if (txfCustomerName.getText().isEmpty() || txfCustomerEmail.getText().isEmpty() || txfCustomerPhoneNumber.getText().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return;
        }

        if (c == null) {
            try {
                customerModel.createCustomer(new Customer(txfCustomerName.getText(), txfCustomerEmail.getText(), txfCustomerPhoneNumber.getText(), txaCustomerNotes.getText()));
                btnApplyCustomer.setDisable(true);
                btnCancelCustomer.setDisable(true);
                clearCustomerDetails();
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }

        } else {
            try {
                c.setName(txfCustomerName.getText());
                c.setEmail(txfCustomerEmail.getText());
                c.setPhoneNumber(txfCustomerPhoneNumber.getText());
                c.setNotes(txaCustomerNotes.getText());
                customerModel.updateCustomer(c);
                ticketModel.clearFilter();
                ticketModel.getObservableTickets().stream().filter(t -> t.getCustomer().equals(c)).forEach(t -> t.setCustomer(c));
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        }

        //refresh tickets data
        try{
            ticketModel.readAllTickets();
            tbvOverviewTickets.setItems(ticketModel.getObservableTickets());
            tbvTicketTabTickets.setItems(ticketModel.getObservableTickets());
        } catch (Exception ex){
            PopUp.showError(ex.getMessage());
        }
    }

    public void handleCancelCustomer(ActionEvent event) {
        Customer c = tbvCustomerTabCustomers.getSelectionModel().getSelectedItem();
        if (c == null) {
            clearCustomerDetails();
            btnApplyCustomer.setDisable(true);
            btnCancelCustomer.setDisable(true);
        } else {
            fillCustomerDetails(c);
        }
    }


    public void clearCustomerDetails() {
        txfCustomerName.clear();
        txfCustomerEmail.clear();
        txfCustomerPhoneNumber.clear();
        txaCustomerNotes.clear();
    }

    private void fillCustomerDetails(Customer c) {
        txfCustomerName.setText(c.getName());
        txfCustomerEmail.setText(c.getEmail());
        txfCustomerPhoneNumber.setText(c.getPhoneNumber());
        txaCustomerNotes.setText(c.getNotes());
    }

    //endregion

    //region TICKET TAB
    public void handleSelectTicket(MouseEvent mouseEvent) {
        Ticket t = tbvTicketTabTickets.getSelectionModel().getSelectedItem();
        if(t != null){
            fillTicketDetails(t);
        }
        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);
    }

    private void fillTicketDetails(Ticket t) {
        //setting up textfields
        /*txfTicketTabFilterEvents.setText(t.getEvent().getName());
        txfTicketTabFilterTicketType.setText(t.getTicketType());
        txfTicketTabFilterCustomers.setText(t.getCustomer().getName());*/

        //fill up ComboBoxes
        cmbEvents.setItems(FXCollections.observableArrayList(t.getEvent()));
        cmbTicketTypes.setItems(FXCollections.observableArrayList(t.getEvent().getTicketTypes()));
        cmbCustomers.setItems(FXCollections.observableArrayList(t.getCustomer()));

        cmbEvents.getSelectionModel().select(0);
        cmbTicketTypes.getSelectionModel().select(t.getEvent().getTicketTypes().indexOf(t.getTicketType()));
        cmbCustomers.getSelectionModel().select(0);
    }

    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleNewTicket(ActionEvent actionEvent) {
        //enable buttons
        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);

        //unselect table view selection
        tbvTicketTabTickets.getSelectionModel().clearSelection();

        clearTicketDetails();

        populateBoxes();
    }

    private void populateBoxes() {
        try {
            eventModel.clearFilter();
            customerModel.clearFilter();
        } catch (Exception e){
            PopUp.showError(e.getMessage());
            return;
        }
        
        cmbEvents.setItems(eventModel.getObservableEvents());
        cmbTicketTypes.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        cmbCustomers.setItems(customerModel.getObservableCustomers());
    }

    private void clearTicketDetails() {
        //clear filters
        txfTicketTabFilterEvents.clear();
        txfTicketTabFilterTicketType.clear();
        txfTicketTabFilterCustomers.clear();
    }

    public void handleRemoveTicket(ActionEvent actionEvent) {
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

    public void handleTicketEventSelected()
    {
        if (cmbEvents.getSelectionModel().getSelectedItem() != null)
            cmbTicketTypes.setItems(FXCollections.observableArrayList(cmbEvents.getSelectionModel().getSelectedItem().getTicketTypes()));
    }

    public void handleCancelTicket(ActionEvent actionEvent) {
    }

    public void handleApplyTicket(ActionEvent actionEvent) {
    }
    //endregion
}
