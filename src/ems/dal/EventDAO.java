package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public List<Event> readAllEvents() throws Exception {
        List<Event> allEvents = new ArrayList<>();

        try(Connection con = ConnectionManager.getConnection()){

            String sqlCommandSelect = "SELECT * FROM Events;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            while(rs.next()){
                allEvents.add(
                        new Event(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("notes"),
                                rs.getTimestamp("start").toLocalDateTime(),
                                rs.getTimestamp("end").toLocalDateTime(),
                                rs.getString("location"),
                                rs.getString("locationGuidance")));
            }
        }

        return allEvents;
    }
}
