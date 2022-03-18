package ems.dal;

import ems.be.User;

import java.util.List;

public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
}
