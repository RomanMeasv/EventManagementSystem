package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User tryLogin(String username, String password) throws Exception {
        User user = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlcommandSelect = "SELECT Users.id, username, [password], [name] FROM Users JOIN UserRoles ON roleId = UserRoles.id WHERE username=? AND [password]=?";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setString(1, username);
            pstmtSelect.setString(2, password);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                if (rs.getString("name").equals("Admin")) { //check if admin
                    user = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                } else if (rs.getString("name").equals("Event Coordinator")) { //check if event coordinator
                    user = new EventCoordinator(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
            return user;
        }
    }

    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception {
        EventCoordinator ecCreated = null;
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlcommandInsert = "INSERT INTO Users(username, [password], roleId) VALUES (?, ?, 2);";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setString(1, ec.getUsername());
            pstmtInsert.setString(2, ec.getPassword());
            pstmtInsert.execute();
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            while (rs.next()) {
                ecCreated = new EventCoordinator(
                        rs.getInt(1),
                        ec.getUsername(),
                        ec.getPassword()
                );
            }
        }
        return ecCreated;
    }

    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        List<EventCoordinator> allEventCoordinators = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Users WHERE roleId = 2;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allEventCoordinators.add(new EventCoordinator(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"))
                );
            }
        }
        return allEventCoordinators;
    }
}
