package ems.gui.model;

import ems.be.Event;
import ems.bll.EventLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    private EventLogic eventLogic;
    private ObservableList<Event> observableEvents;


    public EventModel() throws Exception {
        eventLogic = new EventLogic();
        observableEvents = FXCollections.observableList(eventLogic.readAllEvents());
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

    public void filterEvents(String query) {

    }
}
