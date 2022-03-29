package ems.gui.controller;

import ems.be.Customer;
import ems.gui.model.CustomerModel;
import ems.gui.view.dialogs.CustomerDialog;
import ems.gui.view.util.PopUp;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ems.be.Event;
import ems.gui.model.EventModel;
import ems.gui.view.dialogs.EventDialog;
import ems.gui.view.util.PopUp;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventCoordinatorPageControllerNew implements Initializable {

    public TabPane tbpEventCoordinator;

    public TableView<Event> tbvEvents;
    public TableColumn<Event, String> colEvents;

    public TextField txfFilterEvents;
    public VBox boxEvents;
    public HBox boxEventsButtons;

    public TableView<Customer> tbvCustomers;
    public TableColumn<Customer, String> colCustomers;
    public TextField txfFilterCustomers;
    public VBox boxCustomers;
    public HBox boxCustomersButtons;

    public TableView tbvTickets;
    public TableColumn colTickets;
    public TextField txfFilterTickets;
    public VBox boxTickets;
    public HBox boxTicketsButtons;

    public HBox boxOverviewTab;
    public HBox boxEventsTab;
    public HBox boxCustomersTab;
    public HBox boxTicketsTab;

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

        boxEventsButtons.managedProperty().bind(boxEventsButtons.visibleProperty());
        boxCustomersButtons.managedProperty().bind(boxCustomersButtons.visibleProperty());
        boxTicketsButtons.managedProperty().bind(boxTicketsButtons.visibleProperty());

        tbpEventCoordinator.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            tabChangeListener(newValue.intValue());
        });

        colCustomers.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvCustomers.setItems(customerModel.getObservableCustomers());

        colEvents.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvEvents.setItems(eventModel.getObservableEvents());
    }

    /* EVENTS */
    public void handleFilterEvents(KeyEvent keyEvent) {
        try {
            String query = txfFilterEvents.getText();
            eventModel.filterEvents(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCreateEvent(MouseEvent mouseEvent) {
        EventDialog dialog = new EventDialog();
        Optional<Event> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                eventModel.createEvent(response);
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        });
    }

    public void handleDeleteEvent(MouseEvent mouseEvent) {
        try {
            Event selected = tbvEvents.getSelectionModel().getSelectedItem();
            if (selected != null) {
                eventModel.deleteEvent(selected);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleUpdateEvent(MouseEvent mouseEvent) {
        try {
            Event oldEvent = tbvEvents.getSelectionModel().getSelectedItem();
            if (oldEvent != null) {
                EventDialog dialog = new EventDialog();
                dialog.setFields(oldEvent);
                Optional<Event> result = dialog.showAndWait();
                if (result.isPresent()) {
                    Event updatedEvent = result.get();
                    updatedEvent.setId(oldEvent.getId());
                    eventModel.updateEvent(oldEvent, updatedEvent);
                }
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    /* CUSTOMERS */
    public void handleFilterCustomers(KeyEvent keyEvent) {

    }

    public void handleCreateCustomer(MouseEvent mouseEvent) {
            CustomerDialog dialog = new CustomerDialog();
            Optional<Customer> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    customerModel.createCustomer(response);
                } catch (Exception e) {
                    PopUp.showError(e.getMessage());
                }
            });

    }

    public void handleDeleteCustomer(MouseEvent mouseEvent) {
        try{
            Customer selected = tbvCustomers.getSelectionModel().getSelectedItem();
            if(selected != null){
                customerModel.deleteCustomer(selected);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleUpdateCustomer(MouseEvent mouseEvent) {
        try{
            Customer oldCustomer = tbvCustomers.getSelectionModel().getSelectedItem();
            if(oldCustomer != null){
                CustomerDialog dialog = new CustomerDialog();
                dialog.setFields(oldCustomer);
                Optional<Customer> result = dialog.showAndWait();
                if (result.isPresent()) {
                Customer updatedCustomer = result.get();
                updatedCustomer.setId(oldCustomer.getId());
                customerModel.updateCustomer(oldCustomer, updatedCustomer);
                }
            }
        }catch(Exception e){

        }
    }

    /* TICKETS */
    public void handleFilterTickets(KeyEvent keyEvent) {

    }

    public void handleCreateTicket(MouseEvent mouseEvent) {

    }

    public void handleDeleteTicket(MouseEvent mouseEvent) {

    }

    public void handleUpdateTicket(MouseEvent mouseEvent) {

    }

    /* TAB CHANGING */
    public void tabChangeListener(int newValue) {
        switch (newValue){
            case 0 -> {
                changeToOverviewTab();
            }
            case 1 -> {
                changeToEventsTab();
            }
            case 2 -> {
                changeToCustomersTab();
            }
            case 3 -> {
                changeToTicketsTab();
            }
        }
    }

    private void changeToOverviewTab() {
        boxEventsButtons.setVisible(false);
        boxCustomersButtons.setVisible(false);
        boxTicketsButtons.setVisible(false);

        boxEventsTab.getChildren().remove(boxEvents);
        boxCustomersTab.getChildren().remove(boxCustomers);
        boxTicketsTab.getChildren().remove(boxTickets);

        boxOverviewTab.getChildren().removeAll(boxEvents, boxCustomers, boxTickets);
        boxOverviewTab.getChildren().addAll(boxEvents, boxCustomers, boxTickets);
    }

    private void changeToEventsTab() {
        boxEventsButtons.setVisible(true);
        boxEventsTab.getChildren().add(boxEvents);
    }

    private void changeToCustomersTab() {
        boxCustomersButtons.setVisible(true);
        boxCustomersTab.getChildren().add(boxCustomers);
    }

    private void changeToTicketsTab() {
        boxTicketsButtons.setVisible(true);
        boxTicketsTab.getChildren().add(boxTickets);
    }
}
