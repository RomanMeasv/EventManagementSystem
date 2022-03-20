package ems.bll;

import ems.be.EventCoordinator;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class AdminLogic {

    IDataAccess dataAccess;

    public AdminLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<EventCoordinator> readAllEventCoordinators() throws Exception {
        return dataAccess.readAllEventCoordinators();
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception {
        return dataAccess.createEventCoordinator(ec);
    }
    public void deleteEventCoordinator(EventCoordinator ec) throws Exception {
        dataAccess.deleteEventCoordinator(ec);

    }

    public void updateEventCoordinator(EventCoordinator updatedEC) throws Exception {
        dataAccess.updateEventCoordinator(updatedEC);
    }

    public List<EventCoordinator> filterEventCoordinators(String query) throws Exception {
        if(!query.isEmpty()){
            return dataAccess.filterEventCoordinators(query);
        }
        return readAllEventCoordinators();
    }
}
