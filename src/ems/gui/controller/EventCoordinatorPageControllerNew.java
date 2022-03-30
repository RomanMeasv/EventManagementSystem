package ems.gui.controller;

import ems.be.Customer;
import ems.gui.model.CustomerModel;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
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
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {
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
    public TableView tbvOverviewTickets;
    public TableColumn colOverviewTickets;
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
    public TableView<Customer> tbvCustomers;
    public TextField txfCustomerName, txfCustomerEmail,
            txfCustomerPhoneNumber;
    public TextArea txaCustomerDescription;

    /* MODELS */
    private CustomerModel customerModel;
    private EventModel eventModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            customerModel = new CustomerModel();
            eventModel = new EventModel();
        } catch (Exception e) {
            PopUp.showError(e.getMessage()); //error is custom handled within the logic
        }

        /* SET UP OVERVIEW TAB */
        colOverviewEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvOverviewEvents.setItems(eventModel.getObservableEvents());
        colOverviewCustomers.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvOverviewCustomers.setItems(customerModel.getObservableCustomers());

        /* SET UP EVENTS TAB */
        colEventTabEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEventTabEvents.setItems(eventModel.getObservableEvents());

        /* Disable cancel/apply buttons */
        btnApplyEvent.setDisable(true);
        btnCancelEvent.setDisable(true);
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

    public void handleRemoveEvent() {
        try {
            Event selected = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
            clearEventDetails();
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
            } catch (Exception ex) {
                PopUp.showError(ex.getMessage());
            }
        }
    }

    public void handleAddTicketType(ActionEvent event) {
        if (txfEventTicketType.getText().isEmpty()) {
            return;
        }

        if (ltvEventTicketTypes.getItems().contains(txfEventTicketType.getText())) {
            txfEventName.clear();
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
        } else //an existing event was about to be edited
        {
            fillEventDetails(e);
        }

        btnApplyEvent.setDisable(true);
        btnCancelEvent.setDisable(true);
    }

    public void handleSelectEvent() {
        btnApplyEvent.setDisable(false);
        btnCancelEvent.setDisable(false);
        Event e = tbvEventTabEvents.getSelectionModel().getSelectedItem();
        if (e != null) {
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
        //txfFilterOverviewCustomers
    }

    public void handleSelectCustomer(MouseEvent mouseEvent) {
        Customer c = tbvCustomers.getSelectionModel().getSelectedItem();
        if (c != null) {
            txfCustomerName.setText(c.getName());
            txfCustomerEmail.setText(c.getEmail());
            txfCustomerPhoneNumber.setText(c.getPhoneNumber());
            txaCustomerDescription.setText(c.getNotes());
        }
    }
    //endregion

    //region TICKET TAB
    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleCreateTicket(MouseEvent mouseEvent) {

    }

    public void handleDeleteTicket(MouseEvent mouseEvent) {

    }

    public void handleUpdateTicket(MouseEvent mouseEvent) {

    }
    //endregion
}
