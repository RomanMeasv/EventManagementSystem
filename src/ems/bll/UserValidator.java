package ems.bll;

import ems.be.User;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.UsernameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class UserValidator {

    private IDataAccess dataAccess;

    public UserValidator() {
        dataAccess = DAFacade.getInstance();
    }

    public boolean validateUsername(String username) throws UsernameAlreadyTakenException, UnconnecedDatabaseException {
        boolean isUsernameTaken = false;
        try {
            isUsernameTaken = dataAccess.readAllUsernames().contains(username);
        } catch (Exception e) {
            throw new UnconnecedDatabaseException("Could not read all usernames! Check database connection!", e);
        }
        if (isUsernameTaken) {
            throw new UsernameAlreadyTakenException("Username already taken!");
        }

        return true;
    }
}
