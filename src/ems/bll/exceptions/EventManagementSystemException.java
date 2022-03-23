package ems.bll.exceptions;

public abstract class EventManagementSystemException extends Exception{
    String message;
    Exception exception;

    public EventManagementSystemException(String message, Exception exception)
    {
        this.message = message;
        this.exception = exception;
        exception.printStackTrace();
    }

    public EventManagementSystemException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }
}
