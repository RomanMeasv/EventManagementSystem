package ems.bll.exceptions;

public class UnconnecedDatabaseException extends EventManagementSystemException{
    public UnconnecedDatabaseException(String message, Exception exception) {
        super(message, exception);
    }
}
