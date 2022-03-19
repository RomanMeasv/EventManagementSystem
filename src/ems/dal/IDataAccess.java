package ems.dal;

import ems.be.EventCoordinator;
import ems.be.User;

import java.util.List;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
    EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> readAllEventCoordinators() throws Exception;
}
