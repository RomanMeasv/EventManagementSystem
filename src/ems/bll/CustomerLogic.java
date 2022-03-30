package ems.bll;

import ems.be.Customer;
import ems.bll.exceptions.DatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class CustomerLogic {
    private IDataAccess dataAccess;

    public CustomerLogic() {
        this.dataAccess = DAFacade.getInstance();
    }

    public List<Customer> readAllCustomers() throws DatabaseException {
        try {
            return dataAccess.readAllCustomers();
        }
        catch (Exception exception)
        {
            throw new DatabaseException("Could read all events! Check database connection!", exception);
        }
    }

     public Customer createCustomer(Customer c) throws DatabaseException {
         try {
             return dataAccess.createCustomer(c);
         } catch (Exception exception) {
             throw new DatabaseException("Could not create a customer! Check database connection!", exception);
         }
     }

     public void deleteCustomer(Customer c) throws DatabaseException {
         try {
             dataAccess.deleteCustomer(c);
         } catch (Exception exception) {
             throw new DatabaseException("Could not delete customer! Check database connection!", exception);
         }
     }

     public void updateCustomer(Customer updatedCustomer) throws DatabaseException {
         try {
             dataAccess.updateCustomer(updatedCustomer);
         } catch (Exception exception) {
             throw new DatabaseException("Could not update Customer! Check database connection!", exception);
         }
     }

     public List<Customer> filterCustomers(String query) throws DatabaseException {
         try {
             return  dataAccess.filterCustomer(query);
         } catch (Exception exception) {
             throw new DatabaseException("Could not filter Customers! Check database connection!", exception);
         }
     }

}
