package ems.gui.model;

import ems.be.Event;
import ems.bll.EventLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventModel {
    private EventLogic eventLogic;
    private ObservableList<Event> observableAllEvents;


    public EventModel() throws Exception {
        eventLogic = new EventLogic();
        List<Event> list = eventLogic.readAllEvents();
        observableAllEvents = FXCollections.observableList(list);
    }


    public void createEvent(Event e) throws Exception {
        eventLogic.createEvent(e);
        observableAllEvents.add(e);
    }

    public void deleteEvent(Event e) throws Exception{
        //eventLogic.deleteEvent(e);
    }

    public ObservableList<Event> getObservableAllEvents(){
        return observableAllEvents;
    }


}
