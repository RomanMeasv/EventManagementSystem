package ems.gui.model;

import ems.be.Customer;
import ems.bll.CustomerLogic;
import ems.bll.exceptions.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class CustomerModel {
    private CustomerLogic customerLogic;
    private ObservableList<Customer> allCustomers;

    //Default visibility modifier so only the facade can construct
    CustomerModel() throws Exception {
        customerLogic = new CustomerLogic();
        allCustomers = FXCollections.observableList(customerLogic.readAllCustomers());
    }

    public ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void createCustomer(Customer c) throws Exception {
        Customer created = customerLogic.createCustomer(c);
        if (created != null) {
            allCustomers.add(created);
        }
    }

    public void deleteCustomer(Customer c) throws DatabaseException {
        customerLogic.deleteCustomer(c);
        allCustomers.remove(c);
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerLogic.updateCustomer(customer);
        allCustomers.set(allCustomers.indexOf(customer), customer);
    }

    public FilteredList<Customer> getFilteredCustomers(String query) throws Exception {
        return customerLogic.getFilteredCustomers(query, allCustomers);
    }

    public void filterCustomers(String query) {

    }
}
