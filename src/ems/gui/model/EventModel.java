package ems.gui.model;

import ems.be.Event;
import ems.bll.EventLogic;
import ems.bll.exceptions.DatabaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    private EventLogic eventLogic;
    private ObservableList<Event> observableEvents;

    private static EventModel instance;

    private EventModel() throws Exception {
        eventLogic = new EventLogic();
        observableEvents = FXCollections.observableList(eventLogic.readAllEvents());
    }
    public static EventModel getInstance() throws Exception {
        if(instance == null){
            instance = new EventModel();
        }
        return instance;
    }



    public ObservableList<Event> getObservableEvents() {
        return observableEvents;
    }

    public void createEvent(Event e) throws Exception {
        Event created = eventLogic.createEvent(e);
        if (created != null) {
            observableEvents.add(created);
        }
    }

    public void deleteEvent(Event e) throws Exception {
        eventLogic.deleteEvent(e);
        observableEvents.remove(e);
    }

    public void updateEvent(Event event) throws Exception {
        eventLogic.updateEvent(event);
        observableEvents.set(observableEvents.indexOf(event), event);
    }

    public List<Event> getListOfFiteredEvents(String query) throws Exception {
        return eventLogic.filterEvents(query);
    }
}
