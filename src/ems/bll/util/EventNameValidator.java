package ems.bll.util;

import ems.be.Event;
import ems.bll.EventLogic;
import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class EventNameValidator {
    private EventLogic eventLogic;

    public EventNameValidator() {
        eventLogic = new EventLogic();
    }

    public boolean validate(String eventName) throws NameAlreadyTakenException, UnconnecedDatabaseException {
        boolean isEventNameTaken = eventLogic.readAllEventNames().contains(eventName);
        if (isEventNameTaken) {
            throw new NameAlreadyTakenException("Event name already taken!");
        }

        return true;
    }
}
