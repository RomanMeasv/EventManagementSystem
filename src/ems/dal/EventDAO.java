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

    public void deleteEvent(Event e) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandDelete = "DELETE FROM Events WHERE id=?;";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlCommandDelete);

            pstmtDelete.setInt(1, e.getId());
            pstmtDelete.executeUpdate();
        }
    }

    public void updateEvent(Event e) throws Exception{
        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandUpdate = "UPDATE Events SET [name]=?, [description]=?, notes=?, [start]=?, [end]=?, location=?, locationGuidance=? WHERE id = ?";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlCommandUpdate);

            pstmtUpdate.setString(1, e.getName());
            pstmtUpdate.setString(2, e.getDescription());
            pstmtUpdate.setString(3, e.getNotes());
            pstmtUpdate.setTimestamp(4, Timestamp.valueOf(e.getStart()));
            pstmtUpdate.setTimestamp(5, Timestamp.valueOf(e.getEnd()));
            pstmtUpdate.setString(6, e.getLocation());
            pstmtUpdate.setString(7, e.getLocationGuidance());
            pstmtUpdate.setInt(8, e.getId());

            pstmtUpdate.executeUpdate();
        }
    }
}
