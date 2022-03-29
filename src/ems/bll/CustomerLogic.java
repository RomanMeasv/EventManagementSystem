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

}
