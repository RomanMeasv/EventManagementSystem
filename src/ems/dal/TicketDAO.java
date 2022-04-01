package ems.dal;

import ems.be.Event;
import ems.be.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDAO {
    private EventDAO eventDAO;
    private CustomerDAO customerDAO;

    public TicketDAO() {
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
    }

    public List<Ticket> readAllTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection con = ConnectionManager.getConnection()) {
            String sqlCommandSelect = "SELECT * FROM Tickets";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlCommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            while (rs.next()) {
                tickets.add(
                        new Ticket(
                            UUID.fromString(rs.getString("uuid")),
                            rs.getBoolean("isValid"),
                            eventDAO.getEventById(rs.getInt("eventId")),
                            rs.getString("ticketType"),
                            customerDAO.getCustomerById(rs.getInt("customerId")))
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
}
