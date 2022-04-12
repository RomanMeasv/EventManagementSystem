package ems.bll;

import ems.be.Ticket;
import ems.bll.exceptions.DatabaseException;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class TicketLogic {
    IDataAccess dataAccess;

    public TicketLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<Ticket> readAllTickets() throws Exception {
        try {
            return dataAccess.readAllTickets();
        } catch (Exception e) {
            throw new DatabaseException("Could not read tickets!", e);
        }
    }

    public void createTicket(Ticket ticket) throws Exception {
        try {
            dataAccess.createTicket(ticket);
        } catch (Exception e) {
            throw new DatabaseException("Could not create ticket!", e);
        }
    }

    public void updateTicket(Ticket ticket) throws Exception {
        try {
            dataAccess.updateTicket(ticket);
        } catch (Exception e) {
            throw new DatabaseException("Could not update ticket!", e);
        }
    }

    public void deleteTicket(Ticket ticket) throws Exception {
        try {
            dataAccess.deleteTicket(ticket);
        } catch (Exception e) {
            throw new DatabaseException("Could not delete ticket!", e);
        }
    }

    public FilteredList<Ticket> getFilteredTickets(String query, ObservableList<Ticket> allTickets) {
        return query.isEmpty() ?
                new FilteredList<>(allTickets) :
                allTickets.filtered(ticket -> ticket.toString().toLowerCase().contains(query.toLowerCase()));
    }

    public void saveTicket(File file, BufferedImage image) throws Exception {
        ImageIO.write(image, "png", file);
    }
}
