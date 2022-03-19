package ems.bll;

import ems.be.EventCoordinator;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class AdminLogic {

    IDataAccess dataAccess;

    public AdminLogic(){
        dataAccess = DAFacade.getInstance();
    }

    public List<EventCoordinator> readAllEventCoordinators() {
        return DAFacade.readAllEventCoordinators();
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception {
        return dataAccess.createEventCoordinator(ec);
    }
}
