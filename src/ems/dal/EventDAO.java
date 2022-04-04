package ems.dal;

import ems.be.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public Event createEvent(Event e) throws Exception {
        Event eCreated = null;

        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);

            //create tuple in Events table
            String sqlCommandInsertEvent = "INSERT INTO Events([name], [description], notes, [start], [end], location, locationGuidance) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmtInsertEvent = con.prepareStatement(sqlCommandInsertEvent, Statement.RETURN_GENERATED_KEYS);

            pstmtInsertEvent.setString(1, e.getName());
            pstmtInsertEvent.setString(2, e.getDescription());
            pstmtInsertEvent.setString(3, e.getNotes());
            pstmtInsertEvent.setTimestamp(4, Timestamp.valueOf(e.getStart()));
            pstmtInsertEvent.setTimestamp(5, Timestamp.valueOf(e.getEnd()));
            pstmtInsertEvent.setString(6, e.getLocation());
            pstmtInsertEvent.setString(7, e.getLocationGuidance());

            pstmtInsertEvent.executeUpdate();
            ResultSet rs = pstmtInsertEvent.getGeneratedKeys();

            while(rs.next()) {
                //create tuples in TicketTypes table
                String sqlCommandInsertTicketTypes = "INSERT INTO TicketTypes(eventId, [type]) VALUES (?, ?);";
                PreparedStatement pstmtInsertTicketTypes = con.prepareStatement(sqlCommandInsertTicketTypes);
                for (String type : e.getTicketTypes()) {
                    pstmtInsertTicketTypes.setInt(1, rs.getInt(1));
                    pstmtInsertTicketTypes.setString(2, type);
                    pstmtInsertTicketTypes.addBatch();
                }

                pstmtInsertTicketTypes.executeBatch();

                //construct Event object
                eCreated = new Event(
                        rs.getInt(1),
                        e.getName(),
                        e.getDescription(),
                        e.getNotes(),
                        e.getStart(),
                        e.getEnd(),
                        e.getLocation(),
                        e.getLocationGuidance(),
                        e.getTicketTypes()
                );
            }

            con.commit();
            con.setAutoCommit(true);
        }

        return eCreated;
    }

    public List<Event> readAllEvents() throws Exception {
        List<Event> allEvents = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandSelect = "SELECT * FROM Events;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                allEvents.add(
                        new Event(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("notes"),
                                rs.getTimestamp("start").toLocalDateTime(),
                                rs.getTimestamp("end").toLocalDateTime(),
                                rs.getString("location"),
                                rs.getString("locationGuidance"),
                                readTicketTypes(rs.getInt("id"))));
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
        //no need to delete ticket types, since they are cascade deleted
    }

    public void updateEvent(Event e) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            con.setAutoCommit(false);

            //update Event tuple
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

            //insert ticket types that are not yet present
            String sqlCommandInsert = """
                                        IF NOT EXISTS(SELECT * FROM TicketTypes WHERE eventId = ? AND [type] = ?) 
                                        BEGIN 
                                            INSERT INTO TicketTypes (eventId, type) VALUES (?, ?) 
                                        END""";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlCommandInsert);
            for (String type : e.getTicketTypes()) {
                pstmtInsert.setInt(1, e.getId());
                pstmtInsert.setString(2, type);
                pstmtInsert.setInt(3, e.getId());
                pstmtInsert.setString(4, type);
                pstmtInsert.addBatch();
            }
            pstmtInsert.executeUpdate();

            //remove old ticket type tuples
            String sqlCommandDelete = "DELETE FROM TicketTypes WHERE eventId=? AND type NOT IN (?)";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlCommandDelete);
            pstmtDelete.setInt(1, e.getId());
            pstmtDelete.setString(2, String.join(",", e.getTicketTypes()));

            con.commit();
            con.setAutoCommit(true);
        }
    }

    public List<String> readAllEventNames() throws Exception {
        List<String> allEventNames = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandSelect = "SELECT [name] FROM Events";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                allEventNames.add(rs.getString("name"));
            }
        }
        return allEventNames;
    }

    public List<Event> filterEvents(String query) throws Exception {
        List<Event> filtered = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandFilter = "SELECT * FROM Events WHERE [name] LIKE ?;";
            PreparedStatement pstmtFilter = con.prepareStatement(sqlCommandFilter);
            pstmtFilter.setString(1, "%" + query + "%");
            ResultSet rs = pstmtFilter.executeQuery();

            while (rs.next()) {
                filtered.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("notes"),
                        rs.getTimestamp("start").toLocalDateTime(),
                        rs.getTimestamp("end").toLocalDateTime(),
                        rs.getString("location"),
                        rs.getString("locationGuidance"),
                        readTicketTypes(rs.getInt("id"))
                ));
            }
        }

        return filtered;
    }

    private List<String> readTicketTypes(int eventId) throws Exception {
        List<String> ticketTypes = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandSelect = "SELECT [type] FROM TicketTypes WHERE eventId=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            pstmtSelect.setInt(1, eventId);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                ticketTypes.add(rs.getString("type"));
            }
        }
        return ticketTypes;
    }

    //get event by id
    public Event readEvent(int id) throws Exception {
        Event e = null;

        try (Connection con = ConnectionManager.getConnection()) {

            String sqlCommandSelect = "SELECT * FROM Events WHERE id=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            pstmtSelect.setInt(1, id);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                e = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("notes"),
                        rs.getTimestamp("start").toLocalDateTime(),
                        rs.getTimestamp("end").toLocalDateTime(),
                        rs.getString("location"),
                        rs.getString("locationGuidance"),
                        readTicketTypes(rs.getInt("id"))
                );
            }
        }
        return e;
    }
}
