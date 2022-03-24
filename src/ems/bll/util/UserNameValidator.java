package ems.bll.util;

import ems.bll.UserLogic;
import ems.bll.exceptions.DatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;

public class UserNameValidator {

    private UserLogic userLogic;

    public UserNameValidator() {
        userLogic = new UserLogic();
    }

    public boolean isValid(String username) throws DatabaseException {
        return !userLogic.readAllUsernames().contains(username);
    }
}
