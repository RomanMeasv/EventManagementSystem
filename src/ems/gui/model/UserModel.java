package ems.gui.model;

import ems.be.User;
import ems.bll.UserLogic;
import ems.bll.exceptions.DatabaseException;

public class UserModel {
    private UserLogic userLogic;

    public UserModel() {
         userLogic = new UserLogic();
    }

    public User tryLogin(String username, String password) throws DatabaseException {
        return userLogic.tryLogin(username,password);
    }
}
