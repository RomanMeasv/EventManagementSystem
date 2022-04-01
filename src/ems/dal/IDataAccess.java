package ems.dal;

import ems.be.*;

import java.util.List;


public interface IDataAccess {
    User tryLogin(String username, String password) throws Exception;
    EventCoordinator createEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> readAllEventCoordinators() throws Exception;
    void updateEventCoordinator(EventCoordinator ec) throws Exception;
    void deleteEventCoordinator(EventCoordinator ec) throws Exception;
    List<EventCoordinator> filterEventCoordinators(String query) throws Exception;
    Event createEvent (Event e) throws Exception;
    void deleteEvent (Event e) throws Exception;
    List<Event> readAllEvents() throws Exception;
    void updateEvent(Event e) throws Exception;
    List<String> readAllUsernames() throws Exception;
    List<String> readAllEventNames() throws Exception;
    List<Event> filterEvents(String query) throws Exception;
    Customer createCustomer(Customer c) throws Exception;
    List<Customer> readAllCustomers() throws Exception;
    void updateCustomer(Customer c) throws Exception;
    void deleteCustomer(Customer c) throws Exception;
    List<Customer> filterCustomers(String query) throws Exception;
    List<Ticket> readAllTickets() throws Exception;
    Ticket createTicket(Ticket t) throws Exception;
}
