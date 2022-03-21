package ems.bll;

import ems.be.Event;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class EventLogic {
    IDataAccess dataAccess;

    public EventLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public Event createEvent(Event e) throws Exception {
        return dataAccess.createEvent(e);
    }

    public List<Event> readAllEvents() throws Exception {
        return dataAccess.readAllEvents();
    }

    public void deleteEvent(Event e) throws Exception {
        dataAccess.deleteEvent(e);
    }
}
