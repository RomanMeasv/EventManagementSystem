package ems.bll;

import ems.be.User;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

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
}
