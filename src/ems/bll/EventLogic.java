package ems.bll;

import ems.be.Event;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class EventLogic {
    IDataAccess dataAccess;

    public EventLogic(){dataAccess = DAFacade.getInstance();}

    public void createEvent(Event e) throws Exception {
        dataAccess.createEvent(e);
    }
}
