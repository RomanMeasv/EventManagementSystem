package ems.bll.util;

import ems.bll.EventLogic;
import ems.bll.exceptions.DatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;

public class EventNameValidator {

    private EventLogic eventLogic;

    public EventNameValidator() {
        eventLogic = new EventLogic();
    }

    public boolean isValid(String eventName) throws  DatabaseException {
        return !eventLogic.readAllEventNames().contains(eventName);
    }
}
