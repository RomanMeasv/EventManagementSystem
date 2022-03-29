package ems.gui.model;

import ems.be.Customer;
import ems.bll.CustomerLogic;
import ems.bll.exceptions.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerModel {
    private CustomerLogic customerLogic;
    private ObservableList<Customer> observableCustomers;

    public CustomerModel() throws Exception {
        customerLogic = new CustomerLogic();
        observableCustomers = FXCollections.observableList(customerLogic.readAllCustomers());
    }

    public ObservableList<Customer> getObservableCustomers() {
        return observableCustomers;
    }
}
