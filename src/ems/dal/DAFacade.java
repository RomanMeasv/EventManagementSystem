package ems.dal;

import ems.be.Customer;
import ems.be.Event;
import ems.be.EventCoordinator;
import ems.be.User;
import java.util.List;

public class DAFacade implements IDataAccess {

    private static DAFacade instance; //Singleton instance

    UserDAO userDAO;
    TicketDAO ticketDAO;
    EventDAO eventDAO;
    CustomerDAO customerDAO;

    private DAFacade() {
        userDAO = new UserDAO();
        ticketDAO = new TicketDAO();
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
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
    public List<EventCoordinator> filterEventCoordinators(String query) throws Exception {
        return userDAO.filterEventCoordinators(query);
    }

    @Override
    public Event createEvent(Event e) throws Exception {
        return eventDAO.createEvent(e);
    }

    @Override
    public List<Event> readAllEvents() throws Exception {
        return eventDAO.readAllEvents();
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
    public List<String> readAllEventNames() throws Exception {
        return eventDAO.readAllEventNames();
    }

    @Override
    public List<Event> filterEvents(String query) throws Exception {
        return eventDAO.filterEvents(query);
    }

    @Override
    public Customer createCustomer(Customer c) throws Exception {
        return customerDAO.createCustomer(c);
    }

    @Override
    public List<Customer> readAllCustomers() throws Exception {
        return customerDAO.readAllCustomers();
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
    public List<Customer> filterCustomer(String query) throws Exception {
        return customerDAO.filterCustomers(query);
    }
}
