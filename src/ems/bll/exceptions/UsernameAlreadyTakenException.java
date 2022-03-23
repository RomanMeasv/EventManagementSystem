package ems.bll.exceptions;

public class UsernameAlreadyTakenException extends EventManagementSystemException{
    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
