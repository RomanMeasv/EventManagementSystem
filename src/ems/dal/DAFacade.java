package ems.dal;

import ems.be.*;

import java.util.ArrayList;
import java.util.List;

public class DAFacade implements IDataAccess {

    private static DAFacade instance; //Singleton instance

    List<Event> cachedEvents = new ArrayList<>();
    List<Customer> cachedCustomers = new ArrayList<>();

    UserDAO userDAO;
    TicketDAO ticketDAO;
    EventDAO eventDAO;
    CustomerDAO customerDAO;

    private DAFacade() {
        userDAO = new UserDAO();
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
        ticketDAO = new TicketDAO();
    }

    public static DAFacade getInstance() {
        return instance == null ? instance = new DAFacade() : instance;
    }

    @Override
    public User tryLogin(String username, String password) throws Exception {
        return userDAO.tryLogin(username, password);
    }

    @Override
    public EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception {
        return userDAO.createEventCoordinator(ec);
    }

    @Override
    public List<EventCoordinator> readAllEventCoordinators() throws Exception {
        return userDAO.readAllEventCoordinators();
    }

    @Override
    public void updateEventCoordinator(EventCoordinator ec) throws Exception {
        userDAO.updateEventCoordinator(ec);
    }

    @Override
    public void deleteEventCoordinator(EventCoordinator ec) throws Exception {
        userDAO.deleteEventCoordinator(ec);
    }

    @Override
    public Event createEvent(Event e) throws Exception {
        return eventDAO.createEvent(e);
    }

    @Override
    public List<Event> readAllEvents() throws Exception {
        cachedEvents = eventDAO.readAllEvents();
        return cachedEvents;
    }

    @Override
    public void updateEvent(Event e) throws Exception {
        eventDAO.updateEvent(e);
    }

    @Override
    public void deleteEvent(Event e) throws Exception {
        eventDAO.deleteEvent(e);
    }

    @Override
    public List<String> readAllUsernames() throws Exception {
        return userDAO.readAllUsernames();
    }

    @Override
    public Customer createCustomer(Customer c) throws Exception {
        return customerDAO.createCustomer(c);
    }

    @Override
    public List<Customer> readAllCustomers() throws Exception {
        cachedCustomers = customerDAO.readAllCustomers();
        return cachedCustomers;
    }

    @Override
    public void updateCustomer(Customer c) throws Exception {
        customerDAO.updateCustomer(c);
    }

    @Override
    public void deleteCustomer(Customer c) throws Exception {
        customerDAO.deleteCustomer(c);
    }

    @Override
    public List<Ticket> readAllTickets() throws Exception {
        return ticketDAO.readAllTickets(cachedEvents, cachedCustomers);
    }

    @Override
    public void createTicket(Ticket t) throws Exception {
        ticketDAO.createTicket(t);
    }

    @Override
    public void updateTicket(Ticket t) throws Exception {
        ticketDAO.updateTicket(t);
    }

    @Override
    public void deleteTicket(Ticket t) throws Exception {
        ticketDAO.deleteTicket(t);
    }
}
