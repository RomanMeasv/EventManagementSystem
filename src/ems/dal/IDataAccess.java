package ems.dal;

import ems.be.User;

import java.util.List;

public interface IDataAccess {
    List<User> getAllUsers();
}
