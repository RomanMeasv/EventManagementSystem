package ems.dal;

import ems.be.User;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
}
