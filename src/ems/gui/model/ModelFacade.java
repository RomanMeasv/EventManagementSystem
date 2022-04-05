package ems.gui.model;

import ems.be.Customer;
import ems.be.Event;
import ems.be.Ticket;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

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

    public FilteredList<Event> getFilteredEvents(String query) throws Exception {
        return eventModel.getFilteredEvents(query);
    }

    public FilteredList<Customer> getFilteredCustomers(String query) throws Exception {
        return customerModel.getFilteredCustomers(query);
    }

    public FilteredList<Ticket> getFilteredTickets(String query) throws Exception {
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
    }

    public void createCustomer(Customer customer) throws Exception {
        customerModel.createCustomer(customer);
    }

    public void deleteCustomer(Customer customer) throws Exception {
        customerModel.deleteCustomer(customer);
        ticketModel.getAllTickets().removeIf(ticket -> ticket.getCustomer().equals(customer));
    }

    public void updateCustomer(Customer customer) throws Exception{
        customerModel.updateCustomer(customer);
    }
}
