package ems.bll;

public interface IValidator {
    boolean validate(Object object) throws Exception;
    boolean validate(String username) throws Exception;
}
