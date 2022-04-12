package ems.gui.model;

import ems.be.Ticket;
import ems.bll.TicketLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.awt.image.BufferedImage;
import java.io.File;

public class TicketModel {
    private TicketLogic ticketLogic;
    private ObservableList<Ticket> allTickets;

    //Default visibility modifier so only the facade can construct
    TicketModel() throws Exception {
        ticketLogic = new TicketLogic();
        allTickets = FXCollections.observableList(ticketLogic.readAllTickets());
    }

    public ObservableList<Ticket> getAllTickets() {
        return allTickets;
    }

    public void createTicket(Ticket ticket) throws Exception {
        ticketLogic.createTicket(ticket);
        allTickets.add(ticket);
    }

    public void updateTicket(Ticket ticket) throws Exception {
        ticketLogic.updateTicket(ticket);
        allTickets.set(allTickets.indexOf(ticket), ticket);
    }

    public void deleteTicket(Ticket ticket) throws Exception {
        ticketLogic.deleteTicket(ticket);
        allTickets.remove(ticket);
    }

    public FilteredList<Ticket> getFilteredTickets(String query) {
        return ticketLogic.getFilteredTickets(query, allTickets);
    }

    public void saveTicket(File file, BufferedImage image) throws Exception {
        ticketLogic.saveTicket(file, image);
    }

    public void mailTicket(String recipient, Ticket ticket, File ticketFile) throws Exception {
        ticketLogic.mailTicket(recipient, ticket, ticketFile);
    }
}
