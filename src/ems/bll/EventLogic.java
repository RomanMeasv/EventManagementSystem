package ems.bll;

import ems.be.Event;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

import java.util.List;

public class EventLogic {
    IDataAccess dataAccess;

    public EventLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public Event createEvent(Event e) throws UnconnecedDatabaseException {
        try {
            return dataAccess.createEvent(e);
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not create event! Check database connection!", exception);
        }
    }

    public List<Event> readAllEvents() throws UnconnecedDatabaseException {
        try {
            return dataAccess.readAllEvents();
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not read events! Check database connection!", exception);
        }
    }

    public void deleteEvent(Event e) throws UnconnecedDatabaseException {
        try {
            dataAccess.deleteEvent(e);
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not delete event! Check database connection!", exception);
        }
    }

    public void updateEvent(Event updatedEvent) throws UnconnecedDatabaseException {
        try {
            dataAccess.updateEvent(updatedEvent);
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could not update event! Check database connection!", exception);
        }
    }

    public List<String> readAllEventNames() throws UnconnecedDatabaseException {
        try {
            return dataAccess.readAllEventNames();
        } catch (Exception exception)
        {
            throw new UnconnecedDatabaseException("Could read all event names! Check database connection!", exception);
        }
    }

    public List<Event> filterEvents(String query) throws UnconnecedDatabaseException {
        if(!query.isEmpty()){
            try{
                return dataAccess.filterEvents(query);
            } catch (Exception e){
                throw new UnconnecedDatabaseException("Could not filter events! Check database connection!", e);
            }
        }
        return readAllEvents();
    }
}
