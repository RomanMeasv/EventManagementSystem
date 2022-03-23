package ems.bll;

import ems.be.User;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class UserLogic {

    IDataAccess dataAccess;

    public UserLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public User tryLogin(String username, String password) throws UnconnecedDatabaseException {
        try {
            return dataAccess.tryLogin(username, password);
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not log in! Check database connection!", exception);
        }
    }

    public List<String> readAllUsernames() throws UnconnecedDatabaseException {
        try {
            return dataAccess.readAllUsernames();
        } catch (Exception exception) {
            throw new UnconnecedDatabaseException("Could read all usernames! Check database connection!", exception);
        }
    }
}
