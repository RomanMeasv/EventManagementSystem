package ems.bll.exceptions;

public abstract class EventManagementSystemException extends Exception{
    String message;
    Exception exception;

    public EventManagementSystemException(String message, Exception exception)
    {
        this.message = message + " (Check developer console for more details)";
        this.exception = exception;
        exception.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }
}
