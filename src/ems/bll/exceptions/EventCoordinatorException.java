package ems.bll.exceptions;

public class EventCoordinatorException extends EventManagementSystemException{
    public EventCoordinatorException(String msg, Exception e) {
        super(msg, e);
    }
}
