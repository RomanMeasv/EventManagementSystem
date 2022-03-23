package ems.bll.util;

import ems.bll.exceptions.UnconnecedDatabaseException;
import ems.bll.exceptions.NameAlreadyTakenException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;

public class EventNameValidator {
    private IDataAccess dataAccess;

    public EventNameValidator() {
        dataAccess = DAFacade.getInstance();
    }

    public boolean validate(String eventName) throws NameAlreadyTakenException, UnconnecedDatabaseException {
        boolean isEventNameTaken = false;
        try {
            isEventNameTaken = dataAccess.readAllEventNames().contains(eventName);
        } catch (Exception e) {
            throw new UnconnecedDatabaseException("Could not read all event names! Check database connection!", e);
        }
        if (isEventNameTaken) {
            throw new NameAlreadyTakenException("Event name already taken!");
        }

        return true;
    }
}
