package ems.bll;

import ems.be.User;
import ems.bll.exceptions.UserException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class UserLogic {

    IDataAccess dataAccess;

    public UserLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public User tryLogin(String username, String password) throws UserException {
        try {
            return dataAccess.tryLogin(username, password);
        } catch (Exception exception)
        {
            throw new UserException("Could not log in!", exception);
        }
    }
}
