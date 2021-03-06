package ems.gui.model;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.awt.image.BufferedImage;
import java.io.File;

public class ModelFacade {

    private static ModelFacade instance; //Singleton instance

    /* MODELS */
    private EventModel eventModel;
    private CustomerModel customerModel;
    private TicketModel ticketModel;

    private ModelFacade() throws Exception {
        eventModel = new EventModel();
        customerModel = new CustomerModel();
        ticketModel = new TicketModel();
    }

    public static ModelFacade getInstance() throws Exception {
        return instance == null ? instance = new ModelFacade() : instance;
    }

    public ObservableList<Event> getAllEvents() {
        return eventModel.getAllEvents();
    }

    public ObservableList<Customer> getAllCustomers() {
        return customerModel.getAllCustomers();
    }

    public ObservableList<Ticket> getAllTickets() {
        return ticketModel.getAllTickets();
    }

    public FilteredList<Event> getFilteredEvents(String query) {
        return eventModel.getFilteredEvents(query);
    }

    public FilteredList<Customer> getFilteredCustomers(String query) throws Exception {
        return customerModel.getFilteredCustomers(query);
    }

    public FilteredList<Ticket> getFilteredTickets(String query) {
        return ticketModel.getFilteredTickets(query);
    }

    public void createEvent(Event event) throws Exception {
        eventModel.createEvent(event);
    }

    public void deleteEvent(Event event) throws Exception {
        eventModel.deleteEvent(event);
        ticketModel.getAllTickets().removeIf(ticket -> ticket.getEvent().equals(event));
    }

    public void updateEvent(Event event) throws Exception {
        eventModel.updateEvent(event);
        for (Ticket t : ticketModel.getAllTickets().filtered(t -> t.getEvent().equals(event))) {
            ticketModel.getAllTickets().set(ticketModel.getAllTickets().indexOf(t), t);
        }
        ticketModel.getAllTickets().removeIf(ticket -> ticket.getEvent().equals(event) && !event.getTicketTypes().contains(ticket.getTicketType()));
    }

    public void createCustomer(Customer customer) throws Exception {
        customerModel.createCustomer(customer);
    }

    public void deleteCustomer(Customer customer) throws Exception {
        customerModel.deleteCustomer(customer);
        ticketModel.getAllTickets().removeIf(ticket -> ticket.getCustomer().equals(customer));
    }

    public void updateCustomer(Customer customer) throws Exception {
        customerModel.updateCustomer(customer);
        for (Ticket t : ticketModel.getAllTickets().filtered(t -> t.getCustomer().equals(customer))) {
            ticketModel.getAllTickets().set(ticketModel.getAllTickets().indexOf(t), t);
        }
    }

    public void createTicket(Ticket ticket) throws Exception {
        ticketModel.createTicket(ticket);
    }

    public void deleteTicket(Ticket ticket) throws Exception {
        ticketModel.deleteTicket(ticket);
    }

    public void updateTicket(Ticket ticket) throws Exception {
        ticketModel.updateTicket(ticket);
    }

    public void saveTicket(File file, BufferedImage image) throws Exception {
        ticketModel.saveTicket(file, image);
    }

    public void mailTicket(String recipient, Ticket ticket, File ticketFile) throws Exception {
        ticketModel.mailTicket(recipient, ticket, ticketFile);
    }
}
