package ems.gui.controller.adminPage.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerTabController implements Initializable {
    public ListView<Customer> ltvCustomers;

    public TextField txfFilterCustomers;
    public TextField txfCustomerName;
    public TextField txfCustomerEmail;
    public TextField txfCustomerPhoneNumber;
    public TextArea txaCustomerNotes;

    public ListView<Event> ltvCustomerAttendingEvents;
    public TextField txfFilterAttendingEvents;

    ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        ltvCustomers.setItems(facade.getAllCustomers());

        ltvCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomer) -> {
            if (selectedCustomer != null) {
                fillCustomerDetails(selectedCustomer);
                ltvCustomerAttendingEvents.setItems(FXCollections.observableList(
                        facade.getAllTickets().stream().filter(t -> t.getCustomer().equals(selectedCustomer)).map(t -> t.getEvent()).distinct().collect(java.util.stream.Collectors.toList())));
            } else {
                clearCustomerDetails();
                ltvCustomerAttendingEvents.setItems(FXCollections.observableArrayList());
            }
        });
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        String query = txfFilterCustomers.getText();
        try {
            ltvCustomers.setItems(facade.getFilteredCustomers(query));
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleRemoveCustomer(ActionEvent event) {
        Customer selectedCustomer = ltvCustomers.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            try {
                facade.deleteCustomer(selectedCustomer);
            } catch (Exception e) {
                PopUp.showError(e.getMessage());
            }
        }
    }

    public void handleFilterAttendingEvents(KeyEvent keyEvent) {
        String query = txfFilterAttendingEvents.getText();
        ltvCustomerAttendingEvents.getItems().setAll(facade.getFilteredEvents(query));
    }

    private void fillCustomerDetails(Customer customer) {
        txfCustomerName.setText(customer.getName());
        txfCustomerEmail.setText(customer.getEmail());
        txfCustomerPhoneNumber.setText(customer.getPhoneNumber());
        txaCustomerNotes.setText(customer.getNotes());
    }

    private void clearCustomerDetails() {
        txfCustomerName.clear();
        txfCustomerEmail.clear();
        txfCustomerPhoneNumber.clear();
        txaCustomerNotes.clear();
        ltvCustomerAttendingEvents.getItems().clear();
    }


}
