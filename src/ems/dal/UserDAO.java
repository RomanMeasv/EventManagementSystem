package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.User;

import java.sql.Connection;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO() throws SQLServerException {
        connection = ConnectionManager.getConnection();
    }

    public List<User> getAll() {
        return null;
    }
}
