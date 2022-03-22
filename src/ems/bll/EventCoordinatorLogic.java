package ems.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.Event;
import ems.be.EventCoordinator;
import ems.bll.exceptions.EventCoordinatorException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.sql.SQLException;
import java.util.List;

public class EventCoordinatorLogic {

    IDataAccess dataAccess;

    public EventCoordinatorLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<EventCoordinator> readAllEventCoordinators() throws EventCoordinatorException {
        try {
            return dataAccess.readAllEventCoordinators();
        } catch (Exception exception)
        {
            throw new EventCoordinatorException("Could not read event coordinators!", exception);
        }
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws EventCoordinatorException {
        try {
            return dataAccess.createEventCoordinator(ec);
        } catch (Exception exception) {
            throw new EventCoordinatorException("Could not create event coordinator!", exception);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws EventCoordinatorException {
        try {
            dataAccess.deleteEventCoordinator(ec);
        } catch (Exception exception) {
            throw new EventCoordinatorException("Could not delete event coordinator!", exception);
        }
    }

    public void updateEventCoordinator(EventCoordinator updatedEC) throws EventCoordinatorException {
        try {
            dataAccess.updateEventCoordinator(updatedEC);
        } catch (Exception exception) {
            throw new EventCoordinatorException("Could not update event coordinator!", exception);
        }
    }

    public List<EventCoordinator> filterEventCoordinators(String query) throws EventCoordinatorException {
        if(!query.isEmpty()){
            try {
                return dataAccess.filterEventCoordinators(query);
            } catch (Exception exception) {
                throw new EventCoordinatorException("Could not filter event coordinators!", exception);
            }
        }
        return readAllEventCoordinators();
    }

    public boolean isUsernameTaken(String usernameTyped) throws Exception {
        return dataAccess.isUsernameTaken(usernameTyped);
    }
}
