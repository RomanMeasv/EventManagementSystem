package ems.bll.util;

import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class UserNameValidator {

    private IDataAccess dataAccess;

    public UserNameValidator() {
        dataAccess = DAFacade.getInstance();
    }

    public boolean validate(String username) throws NameAlreadyTakenException, UnconnecedDatabaseException {
        boolean isUsernameTaken = false;
        try {
            isUsernameTaken = dataAccess.readAllUsernames().contains(username);
        } catch (Exception e) {
            throw new UnconnecedDatabaseException("Could not read all usernames! Check database connection!", e);
        }
        if (isUsernameTaken) {
            throw new NameAlreadyTakenException("Username already taken!");
        }

        return true;
    }
}
