package ems.dal;

import ems.be.Event;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.gui.view.ECDialog;
import jdk.jshell.spi.ExecutionControlProvider;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
    EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> readAllEventCoordinators() throws Exception;
    void updateEventCoordinator(EventCoordinator ec) throws Exception;
    void deleteEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> filterEventCoordinators(String query) throws Exception;
    Event createEvent (Event e) throws Exception;
    void deleteEvent (Event e) throws Exception;
    List<Event> readAllEvents() throws Exception;
}
