package ems.bll.exceptions;

public class DatabaseException extends EventManagementSystemException {
    public DatabaseException(String message, Exception exception) {
        super(message, exception);
    }
}
