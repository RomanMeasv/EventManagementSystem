package ems.gui.model;

import ems.be.User;
import ems.bll.LoginLogic;

public class LoginModel {
    private LoginLogic loginLogic;

    public LoginModel() throws Exception {
         loginLogic = new LoginLogic();
    }

    public User tryLogin(String username, String password) throws Exception {
        return loginLogic.tryLogin(username,password);
    }
}
