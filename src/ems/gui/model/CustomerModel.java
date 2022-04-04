package ems.gui.model;

import ems.be.Customer;
import ems.be.Event;
import ems.bll.CustomerLogic;
import ems.bll.exceptions.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

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

    public void createCustomer(Customer c) throws Exception {
        Customer created = customerLogic.createCustomer(c);
        if(created != null){
            observableCustomers.add(created);
        }
    }

    public void deleteCustomer(Customer c) throws DatabaseException {
        customerLogic.deleteCustomer(c);
        observableCustomers.remove(c);
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerLogic.updateCustomer(customer);
        observableCustomers.set(observableCustomers.indexOf(customer), customer);
    }

    public List<Customer> getFilteredCustomerList(String query) throws Exception {
        return customerLogic.filterCustomers(query);
    }
}
