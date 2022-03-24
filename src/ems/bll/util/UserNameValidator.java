package ems.bll.util;

import ems.bll.UserLogic;
import ems.bll.exceptions.DatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;

public class UserNameValidator {

    private UserLogic userLogic;

    public UserNameValidator() {
        userLogic = new UserLogic();
    }

    public boolean validate(String username) throws NameAlreadyTakenException, DatabaseException {
        boolean isUsernameTaken = userLogic.readAllUsernames().contains(username);
        if (isUsernameTaken) {
            throw new NameAlreadyTakenException("Username already taken!");
        }

        return true;
    }
}
