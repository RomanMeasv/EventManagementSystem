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
}
