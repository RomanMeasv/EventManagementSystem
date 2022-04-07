package ems.bll;

import ems.be.EventCoordinator;
import ems.bll.exceptions.DatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class EventCoordinatorLogic {

    private IDataAccess dataAccess;

    public EventCoordinatorLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<EventCoordinator> readAllEventCoordinators() throws DatabaseException {
        try {
            return dataAccess.readAllEventCoordinators();
        } catch (Exception exception) {
            throw new DatabaseException("Could not read event coordinators! Check database connection!", exception);
        }
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws DatabaseException {
        try {
            return dataAccess.createEventCoordinator(ec);
        } catch (Exception exception) {
            throw new DatabaseException("Could not create event coordinator! Check database connection!", exception);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws DatabaseException {
        try {
            dataAccess.deleteEventCoordinator(ec);
        } catch (Exception exception) {
            throw new DatabaseException("Could not delete event coordinator! Check database connection!", exception);
        }
    }

    public void updateEventCoordinator(EventCoordinator updatedEC) throws DatabaseException {
        try {
            dataAccess.updateEventCoordinator(updatedEC);
        } catch (Exception exception) {
            throw new DatabaseException("Could not update event coordinator! Check database connection!", exception);
        }
    }

    public FilteredList<EventCoordinator> filterEventCoordinators(String query, ObservableList<EventCoordinator> allEventCoordinators) throws DatabaseException {
        return query.isEmpty() ?
                new FilteredList<>(allEventCoordinators) :
                allEventCoordinators.filtered(eventCoordinator -> eventCoordinator.getUsername().toLowerCase().contains(query.toLowerCase()));
    }
}
