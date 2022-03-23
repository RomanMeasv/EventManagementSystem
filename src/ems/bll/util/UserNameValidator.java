package ems.bll.util;

import ems.bll.UserLogic;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class UserNameValidator {

    private UserLogic userLogic;

    public UserNameValidator() {
        userLogic = new UserLogic();
    }

    public boolean validate(String username) throws NameAlreadyTakenException, UnconnecedDatabaseException {
        boolean isUsernameTaken = userLogic.readAllUsernames().contains(username);
        if (isUsernameTaken) {
            throw new NameAlreadyTakenException("Username already taken!");
        }

        return true;
    }
}
