package ems.gui.model;

import ems.be.User;
import ems.bll.UserLogic;

public class UserModel {
    private UserLogic userLogic;

    public UserModel() throws Exception {
         userLogic = new UserLogic();
    }

    public User tryLogin(String username, String password) throws Exception {
        return userLogic.tryLogin(username,password);
    }
}
