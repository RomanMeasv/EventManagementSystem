package ems.bll;

import ems.be.Event;
import ems.bll.exceptions.DatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class EventLogic {
    IDataAccess dataAccess;

    public EventLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public Event createEvent(Event e) throws DatabaseException {
        try {
            return dataAccess.createEvent(e);
        } catch (Exception exception)
        {
            throw new DatabaseException("Could not create event! Check database connection!", exception);
        }
    }

    public List<Event> readAllEvents() throws DatabaseException {
        try {
            return dataAccess.readAllEvents();
        } catch (Exception exception)
        {
            throw new DatabaseException("Could not read events! Check database connection!", exception);
        }
    }

    public void deleteEvent(Event e) throws DatabaseException {
        try {
            dataAccess.deleteEvent(e);
        } catch (Exception exception)
        {
            throw new DatabaseException("Could not delete event! Check database connection!", exception);
        }
    }

    public void updateEvent(Event updatedEvent) throws DatabaseException {
        try {
            dataAccess.updateEvent(updatedEvent);
        } catch (Exception exception)
        {
            throw new DatabaseException("Could not update event! Check database connection!", exception);
        }
    }

    public List<String> readAllEventNames() throws DatabaseException {
        try {
            return dataAccess.readAllEventNames();
        } catch (Exception exception)
        {
            throw new DatabaseException("Could read all event names! Check database connection!", exception);
        }
    }

    public List<Event> filterEvents(String query) throws DatabaseException {
        if(!query.isEmpty()){
            try{
                return dataAccess.filterEvents(query);
            } catch (Exception e){
                throw new DatabaseException("Could not filter events! Check database connection!", e);
            }
        }
        return readAllEvents();
    }
}
