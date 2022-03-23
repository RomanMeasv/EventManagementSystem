package ems.bll.exceptions;

public class MissingMandatoryInformationException extends EventManagementSystemException{
    public MissingMandatoryInformationException(String message) {
        super(message);
    }
}
