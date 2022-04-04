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

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EventCoordinatorPageController implements Initializable {
    /* OVERVIEW TAB */
    public ListView<Event> ltvOverviewEvents;
    public ListView<Customer> ltvOverviewCustomers;
    public ListView<Ticket> ltvOverviewTickets;


    /* EVENTS TAB */
    public ListView<Event> ltvEvents;

    /* "DIALOG PANE" */
    public TextField txfEventName,
            txfEventStartDate, txfEventStartTime,
            txfEventEndDate, txfEventEndTime;
    public TextArea txaEventDescription, txaEventNotes, txaEventLocation, txaEventLocationGuidance;
    public ListView<String> ltvEventTicketTypes;
    public TextField txfEventTicketType;
    public Button btnApplyEvent, btnCancelEvent;


    /* CUSTOMERS TAB */
    public ListView<Customer> ltvCustomers;

    /* "DIALOG PANE" */
    public TextField txfCustomerName, txfCustomerEmail,
            txfCustomerPhoneNumber;
    public TextArea txaCustomerNotes;
    public ListView<Event> ltvCustomerAttendingEvents;
    public TextField txfFilterAttendingEvents;
    public Button btnApplyCustomer, btnCancelCustomer;


    /* TICKETS TAB */
    public ListView<Ticket> ltvTickets;

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
            eventModel = new EventModel();
            customerModel = new CustomerModel();
            ticketModel = new TicketModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage()); //error is custom handled within the logic
        }

        /* SET UP OVERVIEW TAB */
        ltvOverviewEvents.setItems(eventModel.getObservableEvents());
        ltvOverviewCustomers.setItems(customerModel.getObservableCustomers());
        ltvOverviewTickets.setItems(ticketModel.getObservableTickets());

        /* SET UP EVENTS TAB */
        ltvEvents.setItems(eventModel.getObservableEvents());
        ltvEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEventListener(newValue);
        });

        /* SET UP CUSTOMERS TAB */
        ltvCustomers.setItems(customerModel.getObservableCustomers());
        ltvCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedCustomerListener(newValue);
        });

        /* SET UP TICKETS TAB */
        ltvTickets.setItems(ticketModel.getObservableTickets());
        ltvTickets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTicketListener(newValue);
        });


        /* Disable cancel/apply buttons */
        disableAllCancelApplyButtons();

        //hide ticket creation/edit specific fields
        handleTicketCreationEditSpecificFields();
    }

    private void handleTicketCreationEditSpecificFields() {
        lblTicketUUID.setVisible(false);
        txfNoTickets.setVisible(false);
        chbTicketValidation.setVisible(false);
    }

    private void disableAllCancelApplyButtons() {
        setDisableApplyEventButtons(true);
        setDisableApplyCustomerButtons(true);
        setDisableApplyTicketsButtons(true);
    }

    private void setDisableApplyEventButtons(boolean disable) {
        btnApplyEvent.setDisable(disable);
        btnCancelEvent.setDisable(disable);
    }

    private void setDisableApplyCustomerButtons(boolean disable) {
        btnApplyCustomer.setDisable(disable);
        btnCancelCustomer.setDisable(disable);
    }

    private void setDisableApplyTicketsButtons(boolean disable) {
        btnApplyTicket.setDisable(disable);
        btnCancelTicket.setDisable(disable);
    }

    // region EVENTS TAB
    private void selectedEventListener(Event newValue) {
        setDisableApplyEventButtons(false);
        fillEventDetails(newValue);
    }

    private void fillEventDetails(Event event) {
        txfEventName.setText(event.getName());
        txaEventDescription.setText(event.getDescription());
        txaEventNotes.setText(event.getNotes());
        txfEventStartTime.setText(event.getStart().toLocalTime().toString());
        txfEventStartDate.setText(event.getStart().toLocalDate().toString());
        txfEventEndTime.setText(event.getEnd().toLocalTime().toString());
        txfEventEndDate.setText(event.getEnd().toLocalDate().toString());
        txaEventLocation.setText(event.getLocation());
        txaEventLocationGuidance.setText(event.getLocationGuidance());
        ltvEventTicketTypes.setItems(FXCollections.observableList(event.getTicketTypes()));
    }

    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = ((TextField) keyEvent.getSource()).getText();
            eventModel.filterEvents(query);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewEvent(ActionEvent actionEvent) {
        setDisableApplyEventButtons(false);
        ltvEvents.getSelectionModel().clearSelection();
        clearEventDetails();
    }

    public void handleRemoveEvent() {
        try {
            Event selected = ltvEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }

            setDisableApplyEventButtons(true);
            ltvEvents.getSelectionModel().clearSelection();
            clearEventDetails();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        refreshTickets();
    }

    public void handleFilterTicketTypes(KeyEvent keyEvent) {
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

    public void handleApplyEvent() {
        Event selectedEvent = ltvEvents.getSelectionModel().getSelectedItem();
        //check if everything is correct
        if (txfEventName.getText().isEmpty() || txfEventStartDate.getText().isEmpty() || txfEventStartTime.getText().isEmpty() ||
                txfEventEndDate.getText().isEmpty() || txfEventEndTime.getText().isEmpty() || txaEventLocation.getText().isEmpty() || ltvEventTicketTypes.getItems().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields and add at least one ticket type! (*)");
            return;
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

        if (selectedEvent == null) { //it's a new event
            if (eventModel.getObservableEvents().stream().map(Event::getName).toList().contains(txfEventName.getText())) {
                PopUp.showError("Event name already in use!");
                return;
            }

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
            if (eventModel.getObservableEvents().stream().map(Event::getName).toList().contains(txfEventName.getText())
                    && !txfEventName.getText().equals(selectedEvent.getName())) {
                PopUp.showError("Event name already in use!");
                return;
            }

            try {
                selectedEvent.setName(txfEventName.getText());
                selectedEvent.setDescription(txaEventDescription.getText());
                selectedEvent.setNotes(txaEventNotes.getText());
                selectedEvent.setStart(start);
                selectedEvent.setEnd(end);
                selectedEvent.setLocation(txaEventLocation.getText());
                selectedEvent.setLocationGuidance(txaEventLocationGuidance.getText());
                selectedEvent.setTicketTypes(ltvEventTicketTypes.getItems());
                eventModel.updateEvent(selectedEvent);

            } catch (Exception ex) {
                PopUp.showError(ex.getMessage());
            }
        }
        refreshTickets();
    }

    public void handleCancelEvent() {
        Event e = ltvEvents.getSelectionModel().getSelectedItem();
        if (e == null) { //a new event was about to be created
            clearEventDetails();
            btnApplyEvent.setDisable(true);
            btnCancelEvent.setDisable(true);
        } else //an existing event was about to be edited
        {
            fillEventDetails(e);
        }
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

    private void refreshTickets() {
        try {
            ticketModel.clearFilter();
            ltvOverviewTickets.getItems().clear();
            ltvOverviewTickets.getItems().addAll(ticketModel.getObservableTickets());
            ltvTickets.getItems().clear();
            ltvTickets.getItems().addAll(ticketModel.getObservableTickets());
        } catch (Exception ex) {
            PopUp.showError(ex.getMessage());
        }
    }
    //endregion

    // region CUSTOMER TAB
    private void selectedCustomerListener(Customer newValue) {
        btnApplyCustomer.setDisable(false);
        btnCancelCustomer.setDisable(false);
        fillCustomerDetails(newValue);
    }

    private void fillCustomerDetails(Customer customer) {
        txfCustomerName.setText(customer.getName());
        txfCustomerEmail.setText(customer.getEmail());
        txfCustomerPhoneNumber.setText(customer.getPhoneNumber());
        txaCustomerNotes.setText(customer.getNotes());
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = ((TextField) keyEvent.getSource()).getText();
            customerModel.filterCustomers(query);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewCustomer(ActionEvent event) {
        btnApplyCustomer.setDisable(false);
        btnCancelCustomer.setDisable(false);
        ltvCustomers.getSelectionModel().clearSelection();
        clearCustomerDetails();
    }

    public void handleRemoveCustomer(ActionEvent event) {
        try {
            Customer selected = ltvCustomers.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customerModel.deleteCustomer(selected);
            }

            btnApplyCustomer.setDisable(true);
            btnCancelCustomer.setDisable(true);
            clearCustomerDetails();
            ltvCustomers.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        refreshTickets();
    }

    public void handleFilterAttendingEvents(KeyEvent keyEvent) {
    }

    public void handleApplyCustomer(ActionEvent event) {
        Customer c = ltvCustomers.getSelectionModel().getSelectedItem();
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
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        }
        refreshTickets();
    }

    public void handleCancelCustomer(ActionEvent event) {
        Customer c = ltvCustomers.getSelectionModel().getSelectedItem();
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
    //endregion

    //region TICKET TAB
    private void selectedTicketListener(Ticket newValue) {
        clearTicketComboBoxFilters();

        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);

        txfNoTickets.setVisible(false);

        lblTicketUUID.setVisible(true);
        chbTicketValidation.setVisible(true);

        fillTicketDetails(newValue);
    }

    private void clearTicketComboBoxFilters() {
        txfTicketTabFilterEvents.clear();
        txfTicketTabFilterTicketType.clear();
        txfTicketTabFilterCustomers.clear();
    }

    private void fillTicketDetails(Ticket ticket) {
        //fill up ComboBoxes
        cmbEvents.setItems(FXCollections.observableArrayList(ticket.getEvent()));
        cmbTicketTypes.setItems(FXCollections.observableArrayList(ticket.getEvent().getTicketTypes()));
        cmbCustomers.setItems(FXCollections.observableArrayList(ticket.getCustomer()));

        cmbEvents.getSelectionModel().select(0);
        cmbTicketTypes.getSelectionModel().select(ticket.getEvent().getTicketTypes().indexOf(ticket.getTicketType()));
        cmbCustomers.getSelectionModel().select(0);

        lblTicketUUID.setText(ticket.getUuid().toString());
        chbTicketValidation.setSelected(ticket.isValid());
    }

    public void handleFilterTickets(KeyEvent keyEvent) {
        try {
            String query = ((TextField) keyEvent.getSource()).getText();
            ticketModel.filterTickets(query);
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewTicket(ActionEvent actionEvent) {
        //enable buttons
        btnApplyTicket.setDisable(false);
        btnCancelTicket.setDisable(false);

        ltvTickets.getSelectionModel().clearSelection();
        clearTicketComboBoxFilters();
        clearTicketDetails();
        setUpFieldsForNewTicket();
    }

    private void setUpFieldsForNewTicket() {
        //set up ComboBoxes
        try {
            eventModel.clearFilter();
            customerModel.clearFilter();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
            return;
        }

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
        Ticket t = ltvTickets.getSelectionModel().getSelectedItem();
        if (t != null) {
            try {
                ticketModel.deleteTicket(t);
                ltvTickets.getSelectionModel().clearSelection();
                clearTicketDetails();
                clearTicketComboBoxFilters();
                btnApplyTicket.setDisable(true);
                btnCancelTicket.setDisable(true);
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        }
    }

    public void handleFilterEventComboBox(KeyEvent keyEvent) {
        String query = txfTicketTabFilterEvents.getText();
        try {
            if (txfTicketTabFilterEvents.getText().isEmpty()) {
                cmbEvents.hide();
            } else {
                List<Event> filtered = eventModel.getListOfFiteredEvents(query);
                cmbEvents.setItems(FXCollections.observableArrayList(filtered));
                cmbEvents.hide();
                cmbEvents.setVisibleRowCount(filtered.size());
                cmbEvents.show();
            }

        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }


    }

    public void handleFilterTicketTypeComboBox(KeyEvent keyEvent) {

    }

    public void handleFilterCustomerComboBox(KeyEvent keyEvent) {
        String query = txfTicketTabFilterCustomers.getText();
        try {
            if (txfTicketTabFilterCustomers.getText().isEmpty()) {
                cmbCustomers.hide();
            } else {
                List<Customer> filtered = customerModel.getFilteredCustomerList(query);
                cmbCustomers.setItems(FXCollections.observableArrayList(filtered));
                cmbCustomers.hide();
                cmbCustomers.setVisibleRowCount(filtered.size());
                cmbCustomers.show();
            }

        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleEventForTicketSelected() {
        if (cmbEvents.getSelectionModel().getSelectedItem() != null)
            cmbTicketTypes.setItems(FXCollections.observableArrayList(cmbEvents.getSelectionModel().getSelectedItem().getTicketTypes()));
    }

    public void handleCancelTicket(ActionEvent actionEvent) {
        Ticket t = ltvTickets.getSelectionModel().getSelectedItem();
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

        Ticket t = ltvTickets.getSelectionModel().getSelectedItem();
        if (t == null) {
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
                PopUp.showError(e.getMessage());
            }
            clearTicketDetails();
            clearTicketComboBoxFilters();
            btnCancelTicket.setDisable(true);
            btnApplyTicket.setDisable(true);
        } else //TODO: update the ticket
        {
            t.setValid(chbTicketValidation.isSelected());
            t.setTicketType(cmbTicketTypes.getSelectionModel().getSelectedItem());
            try {
                ticketModel.updateTicket(t);
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        }
        clearTicketComboBoxFilters();
    }
    //endregion
}
