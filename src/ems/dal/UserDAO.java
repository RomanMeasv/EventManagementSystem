package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {


    public UserDAO() {

    }

    public User tryLogin(String username, String password) throws Exception {
        User user = null;
        try (Connection connection = ConnectionManager.getConnection()) {
            //check if admin
            String sqlcommandSelect = "SELECT Users.id, username, [password], [name] FROM Users JOIN UserRoles ON roleId = UserRoles.id WHERE username=? AND [password]=?";
            PreparedStatement pstmtSelect = connection.prepareStatement(sqlcommandSelect);
            pstmtSelect.setString(1, username);
            pstmtSelect.setString(2, password);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                if (rs.getString("name").equals("Admin")) {
                    user = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                } else if (rs.getString("name").equals("Event Coordinator")) {
                    user = new EventCoordinator(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
            return user;
        }
    }
}
