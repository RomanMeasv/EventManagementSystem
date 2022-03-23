package ems.bll;

import ems.be.EventCoordinator;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import java.util.List;

public class EventCoordinatorLogic {

    private IDataAccess dataAccess;

    public EventCoordinatorLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<EventCoordinator> readAllEventCoordinators() throws UnconnecedDatabaseException {
        try {
            return dataAccess.readAllEventCoordinators();
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not read event coordinators! Check database connection!", exception);
        }
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws UnconnecedDatabaseException {
        try {
            return dataAccess.createEventCoordinator(ec);
        } catch (Exception exception) {
            throw new UnconnecedDatabaseException("Could not create event coordinator! Check database connection!", exception);
        }
    }

    public void deleteEventCoordinator(EventCoordinator ec) throws UnconnecedDatabaseException {
        try {
            dataAccess.deleteEventCoordinator(ec);
        } catch (Exception exception) {
            throw new UnconnecedDatabaseException("Could not delete event coordinator! Check database connection!", exception);
        }
    }

    public void updateEventCoordinator(EventCoordinator updatedEC) throws UnconnecedDatabaseException {
        try {
            dataAccess.updateEventCoordinator(updatedEC);
        } catch (Exception exception) {
            throw new UnconnecedDatabaseException("Could not update event coordinator! Check database connection!", exception);
        }
    }

    public List<EventCoordinator> filterEventCoordinators(String query) throws UnconnecedDatabaseException {
        if(!query.isEmpty()){
            try {
                return dataAccess.filterEventCoordinators(query);
            } catch (Exception exception) {
                throw new UnconnecedDatabaseException("Could not filter event coordinators! Check database connection!", exception);
            }
        }
        return readAllEventCoordinators();
    }
}
