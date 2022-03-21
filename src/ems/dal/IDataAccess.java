package ems.dal;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.gui.view.ECDialog;

import java.util.List;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
    EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> readAllEventCoordinators() throws Exception;
    void updateEventCoordinator(EventCoordinator ec) throws Exception;
    void deleteEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> filterEventCoordinators(String query) throws Exception;
    Event createEvent(Event e) throws Exception;
}
