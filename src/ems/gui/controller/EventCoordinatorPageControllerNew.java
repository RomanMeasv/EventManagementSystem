package ems.gui.controller;

import ems.be.Customer;
import ems.bll.exceptions.DatabaseException;
import ems.bll.util.EventNameValidator;
import ems.gui.model.CustomerModel;
import ems.gui.view.dialogs.CustomerDialog;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.EventDialog;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {
    EventNameValidator eventNameValidator;
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
        eventNameValidator = new EventNameValidator();
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

    public void handleNewEvent(MouseEvent mouseEvent) {
        btnApplyEvent.setDisable(false);
        btnCancelEvent.setDisable(false);
        
    }

    public void handleRemoveEvent(MouseEvent mouseEvent) {
        try {
            Event selected = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleApplyEvent(MouseEvent mouseEvent) {
        try {
            Event oldEvent = tbvEventTabEvents.getSelectionModel().getSelectedItem();
            /*if (oldEvent != null) {
                EventDialog dialog = new EventDialog();
                dialog.setFields(oldEvent);
                Optional<Event> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Event updatedEvent = result.get();
                    updatedEvent.setId(oldEvent.getId());
                    eventModel.updateEvent(oldEvent, updatedEvent);
                }
            }*/
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleSelectEvent(MouseEvent mouseEvent) {
        Event e = tbvEventTabEvents.getSelectionModel().getSelectedItem();
        if (e != null) {
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
    }


    //endregion

    // region CUSTOMER TAB
    public void handleFilterCustomers(KeyEvent keyEvent) {
        //txfFilterOverviewCustomers
    }

    public void handleSelectCustomer(MouseEvent mouseEvent) {
        Customer c = tbvCustomers.getSelectionModel().getSelectedItem();
        if(c != null){
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

    public void startDateKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void startTimeKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void endDateKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void endTimeKeyTypedHandle(KeyEvent keyEvent) {

    }

    public void handleAddTicketType(ActionEvent event) {

    }

    public void handleRemoveTicketType(ActionEvent event) {

    }




    //endregion
}
