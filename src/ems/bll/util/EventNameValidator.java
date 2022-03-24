package ems.bll.util;

import ems.bll.EventLogic;
import ems.bll.exceptions.DatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;

public class EventNameValidator {
    private EventLogic eventLogic;

    public EventNameValidator() {
        eventLogic = new EventLogic();
    }

    public boolean validate(String eventName) throws NameAlreadyTakenException, DatabaseException {
        boolean isEventNameTaken = eventLogic.readAllEventNames().contains(eventName);
        if (isEventNameTaken) {
            throw new NameAlreadyTakenException("Event name already taken!");
        }

        return true;
    }
}
