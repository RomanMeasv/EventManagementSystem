package ems.dal;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDAO {

    public List<Ticket> readAllTickets(List<Event> cachedEvents, List<Customer> cachedCustomers) throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandSelect = "SELECT * FROM Tickets";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("eventId");
                int customerId = rs.getInt("customerId");
                tickets.add(
                        new Ticket(
                                UUID.fromString(rs.getString("uuid")),
                                rs.getBoolean("isValid"),
                                cachedEvents.stream().filter(e -> e.getId() == eventId).findFirst().orElse(null),
                                rs.getString("ticketType"),
                                cachedCustomers.stream().filter(c -> c.getId() == customerId).findFirst().orElse(null))
                );
            }
        }

        return tickets;
    }

    //create ticket
    public Ticket createTicket(Ticket ticket) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandInsert = "INSERT INTO Tickets (uuid, isValid, eventId, ticketType, customerId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlCommandInsert);
            pstmtInsert.setString(1, ticket.getUuid().toString());
            pstmtInsert.setBoolean(2, ticket.isValid());
            pstmtInsert.setInt(3, ticket.getEvent().getId());
            pstmtInsert.setString(4, ticket.getTicketType());
            pstmtInsert.setInt(5, ticket.getCustomer().getId());
            pstmtInsert.executeUpdate();
        }
        return ticket;
    }

    //update ticket
    public void updateTicket(Ticket ticket) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandUpdate = "UPDATE Tickets SET isValid = ?, ticketType = ? WHERE uuid = ?";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlCommandUpdate);
            pstmtUpdate.setBoolean(1, ticket.isValid());
            pstmtUpdate.setString(2, ticket.getTicketType());
            pstmtUpdate.setString(3, ticket.getUuid().toString());
            pstmtUpdate.executeUpdate();
        }
    }

    public void deleteTicket(Ticket ticket) throws Exception {
        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandDelete = "DELETE FROM Tickets WHERE uuid = ?";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlCommandDelete);
            pstmtDelete.setString(1, ticket.getUuid().toString());
            pstmtDelete.executeUpdate();
        }
    }
}
