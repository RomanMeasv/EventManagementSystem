package ems.bll;

import ems.be.User;
import ems.bll.exceptions.UserException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class UserValidator implements IValidator {

    private IDataAccess dataAccess;

    public UserValidator() {
        dataAccess = DAFacade.getInstance();
    }

    @Override
    public boolean validate(Object object) throws Exception {
        User user = (User) object;
        if (dataAccess.readAllUsernames().contains(user.getUsername())) {
            throw new UserException("Username already taken!", new Exception());
        }
        return true;
    }

    @Override
    public boolean validate(String username) throws Exception {
        if (dataAccess.readAllUsernames().contains(username)) {
            throw new UserException("Username already taken!", new Exception());
        }
        return true;
    }
}
