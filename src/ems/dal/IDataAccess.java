package ems.dal;

import ems.be.EventCoordinator;
import ems.be.User;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
    EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception;
}
