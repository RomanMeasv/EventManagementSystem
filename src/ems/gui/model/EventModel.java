package ems.gui.model;

import ems.be.Event;
import ems.bll.EventLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class EventModel {
    private EventLogic eventLogic;
    private ObservableList<Event> allEvents;

    //Default visibility modifier so only the facade can construct
    EventModel() throws Exception {
        eventLogic = new EventLogic();
        allEvents = FXCollections.observableList(eventLogic.readAllEvents());
    }

    public ObservableList<Event> getAllEvents() {
        return allEvents;
    }

    public void createEvent(Event event) throws Exception {
        Event created = eventLogic.createEvent(event);
        if (created != null) {
            allEvents.add(created);
        }
    }

    public void deleteEvent(Event event) throws Exception {
        eventLogic.deleteEvent(event);
        allEvents.remove(event);
    }

    public void updateEvent(Event event) throws Exception {
        eventLogic.updateEvent(event);
        allEvents.set(allEvents.indexOf(event), event);
    }

    public FilteredList<Event> getFilteredEvents(String query) {
        return eventLogic.getFilteredEvents(query, allEvents);
    }
}
