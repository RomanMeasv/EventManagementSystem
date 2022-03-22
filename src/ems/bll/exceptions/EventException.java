package ems.bll.exceptions;

public class EventException extends EventManagementSystemException{
    public EventException(String msg, Exception e) {
        super(msg, e);
    }
}
