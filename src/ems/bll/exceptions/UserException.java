package ems.bll.exceptions;

public class UserException extends EventManagementSystemException{
    public UserException(String msg, Exception e) {
        super(msg, e);
    }
}
