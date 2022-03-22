package ems.bll;

import ems.be.Event;
import ems.bll.exceptions.EventException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class EventLogic {
    IDataAccess dataAccess;

    public EventLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public Event createEvent(Event e) throws EventException {
        try {
            return dataAccess.createEvent(e);
        } catch (Exception exception)
        {
            throw new EventException("Could not create event!", exception);
        }
    }

    public List<Event> readAllEvents() throws EventException {
        try {
            return dataAccess.readAllEvents();
        } catch (Exception exception)
        {
            throw new EventException("Could not read events!", exception);
        }
    }

    public void deleteEvent(Event e) throws EventException {
        try {
            dataAccess.deleteEvent(e);
        } catch (Exception exception)
        {
            throw new EventException("Could not delete event!", exception);
        }
    }

    public void updateEvent(Event updatedEvent) throws EventException {
        try {
            dataAccess.updateEvent(updatedEvent);
        } catch (Exception exception)
        {
            throw new EventException("Could not update event!", exception);
        }
    }
}
