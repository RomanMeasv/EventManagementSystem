package ems.gui.controller.coordinatorPage.tabs;

import ems.be.Customer;
import ems.be.Event;
import ems.gui.model.ModelFacade;
import ems.gui.view.util.PopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    public Button btnCancelCustomer;
    public Button btnApplyCustomer;

    private ModelFacade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            facade = ModelFacade.getInstance();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }

        ltvCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedCustomer) -> {
            if (selectedCustomer != null) {
                selectedCustomerListener(selectedCustomer);
                ltvCustomerAttendingEvents.setItems(FXCollections.observableList(
                        facade.getAllTickets().stream().filter(t -> t.getCustomer().equals(selectedCustomer)).map(t -> t.getEvent()).distinct().collect(java.util.stream.Collectors.toList())));
            }
            else {
                clearCustomerDetails();
                ltvCustomerAttendingEvents.setItems(FXCollections.observableArrayList());
            }
        });

        ltvCustomers.setItems(facade.getAllCustomers());
    }

    public void handleFilterCustomers(KeyEvent keyEvent) {
        try {
            String query = txfFilterCustomers.getText();
            ltvCustomers.setItems(facade.getFilteredCustomers(query));
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleNewCustomer(ActionEvent event) {
        setDisableApplyButtons(false);

        ltvCustomers.getSelectionModel().clearSelection();

        clearCustomerDetails();
    }

    public void handleRemoveCustomer(ActionEvent event) {
        try {
            Customer selected = ltvCustomers.getSelectionModel().getSelectedItem();
            if (selected == null) {
                return;
            }

            facade.deleteCustomer(selected);

            setDisableApplyButtons(true);

            clearCustomerDetails();

            ltvCustomers.getSelectionModel().clearSelection();
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
    }

    public void handleFilterAttendingEvents(KeyEvent keyEvent) {
        String query = txfFilterAttendingEvents.getText();
        ltvCustomerAttendingEvents.setItems(facade.getFilteredEvents(query));
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

    public void handleApplyCustomer(ActionEvent event) {
        Customer selectedCustomer = ltvCustomers.getSelectionModel().getSelectedItem();
        if (txfCustomerName.getText().isEmpty() ||
                txfCustomerEmail.getText().isEmpty() ||
                txfCustomerPhoneNumber.getText().isEmpty()) {
            PopUp.showError("Please fill in all the mandatory fields! (*)");
            return;
        }

        try {
            if (selectedCustomer == null) { //it's a new customer
                facade.createCustomer(
                        new Customer(
                                txfCustomerName.getText(),
                                txfCustomerEmail.getText(),
                                txfCustomerPhoneNumber.getText(),
                                txaCustomerNotes.getText()));

                setDisableApplyButtons(true);

                clearCustomerDetails();
            } else { //it's an existing customer
                selectedCustomer.setName(txfCustomerName.getText());
                selectedCustomer.setEmail(txfCustomerEmail.getText());
                selectedCustomer.setPhoneNumber(txfCustomerPhoneNumber.getText());
                selectedCustomer.setNotes(txaCustomerNotes.getText());

                facade.updateCustomer(selectedCustomer);
            }
        } catch (Exception e) {
            PopUp.showError(e.getMessage());
        }
        //ltvCustomers.refresh(); //solves many problems yes
    }

    private void selectedCustomerListener(Customer selectedCustomer) {
        setDisableApplyButtons(false);
        fillCustomerDetails(selectedCustomer);
    }

    private void setDisableApplyButtons(boolean value) {
        btnApplyCustomer.setDisable(value);
        btnCancelCustomer.setDisable(value);
    }

    private void clearCustomerDetails() {
        txfCustomerName.clear();
        txfCustomerEmail.clear();
        txfCustomerPhoneNumber.clear();
        txaCustomerNotes.clear();
    }

    private void fillCustomerDetails(Customer customer) {
        txfCustomerName.setText(customer.getName());
        txfCustomerEmail.setText(customer.getEmail());
        txfCustomerPhoneNumber.setText(customer.getPhoneNumber());
        txaCustomerNotes.setText(customer.getNotes());
    }
}
