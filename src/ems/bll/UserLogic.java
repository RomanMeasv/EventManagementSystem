package ems.bll;

import ems.be.User;
import ems.bll.exceptions.DatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class UserLogic {

    IDataAccess dataAccess;

    public UserLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public User tryLogin(String username, String password) throws DatabaseException {
        try {
            return dataAccess.tryLogin(username, password);
        } catch (Exception exception)
        {
            throw new DatabaseException("Could not log in! Check database connection!", exception);
        }
    }

    public List<String> readAllUsernames() throws DatabaseException {
        try {
            return dataAccess.readAllUsernames();
        } catch (Exception exception) {
            throw new DatabaseException("Could read all usernames! Check database connection!", exception);
        }
    }
}
