package ems.gui.model;

import ems.be.Ticket;
import ems.bll.TicketLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketModel {
    private TicketLogic ticketLogic;
    private ObservableList<Ticket> observableTickets;

    public TicketModel() throws Exception {
        ticketLogic = new TicketLogic();
        observableTickets = FXCollections.observableList(ticketLogic.readAllTickets());
    }

    public ObservableList<Ticket> getObservableTickets() {
        return observableTickets;
    }

    public void readAllTickets() throws Exception{
        observableTickets = FXCollections.observableList(ticketLogic.readAllTickets());
    }

    public void clearFilter() throws Exception {
        observableTickets = FXCollections.observableList(ticketLogic.readAllTickets());
    }
}
