package ems.gui.model;

import ems.be.Event;
import ems.bll.EventLogic;

public class EventModel {
    private EventLogic eventLogic;

    public EventModel(){eventLogic = new EventLogic();}

    public void createEvent(Event e) throws Exception {
        eventLogic.createEvent(e);
    }
}
