package ems.dal;

import ems.be.Event;
import ems.be.EventCoordinator;

import java.sql.*;

public class EventDAO {
    public Event createEvent(Event e) throws Exception {
        Event eCreated = null;

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandInsert = "INSERT INTO Events([name], [description], notes, [start], [end], location, locationGuidance) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlCommandInsert, Statement.RETURN_GENERATED_KEYS);

            pstmtInsert.setString(1, e.getName());
            pstmtInsert.setString(2, e.getDescription());
            pstmtInsert.setString(3, e.getNotes());
            pstmtInsert.setTimestamp(4, Timestamp.valueOf(e.getStart()));
            pstmtInsert.setTimestamp(5, Timestamp.valueOf(e.getEnd()));
            pstmtInsert.setString(6, e.getLocation());
            pstmtInsert.setString(7, e.getLocationGuidance());

            pstmtInsert.executeUpdate();
            ResultSet rs = pstmtInsert.getGeneratedKeys();

            while (rs.next()) {
                eCreated = new Event(
                        rs.getInt(1),
                        e.getName(),
                        e.getDescription(),
                        e.getNotes(),
                        e.getStart(),
                        e.getEnd(),
                        e.getLocation(),
                        e.getLocationGuidance()
                );
            }
        }

        return eCreated;
    }
}
