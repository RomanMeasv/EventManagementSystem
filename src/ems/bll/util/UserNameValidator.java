package ems.bll.util;

import ems.be.User;
import ems.bll.exceptions.MissingMandatoryInformationException;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.UsernameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.ArrayList;
import java.util.List;

public class UserNameValidator {

    private IDataAccess dataAccess;

    public UserNameValidator() {
        dataAccess = DAFacade.getInstance();
    }

    public boolean validate(String username) throws UsernameAlreadyTakenException, UnconnecedDatabaseException {
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
