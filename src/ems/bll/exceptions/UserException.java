package ems.bll.exceptions;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;

public class UserException extends EventManagementSystemException{
    public UserException(String msg, Exception e) {
        super(msg, e);
    }
}
