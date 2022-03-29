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

    public void createCustomer(Customer c) throws Exception {
        Customer created = customerLogic.createCustomer(c);
        if(created != null){
            observableCustomers.add(c);
        }
    }

    public void deleteCustomer(Customer c) throws DatabaseException {
        customerLogic.deleteCustomer(c);
        observableCustomers.remove(c);
    }

    public void updateCustomer(Customer oldCustomer, Customer updatedCustomer) throws Exception {
        customerLogic.updateCustomer(updatedCustomer);
        observableCustomers.set(oldCustomer.getId(), updatedCustomer);
    }
}