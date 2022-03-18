package ems.bll;

import ems.be.User;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class LoginLogic {

    IDataAccess dataAccess;

    public LoginLogic() throws Exception {
        dataAccess = DAFacade.getInstance();
    }

    public User tryLogin(String username, String password) throws Exception {
        return dataAccess.tryLogin(username, password);
    }
}
