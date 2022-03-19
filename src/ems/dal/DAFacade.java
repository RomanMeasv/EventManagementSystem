package ems.dal;

import ems.be.EventCoordinator;
import ems.be.User;

import java.util.List;

public class DAFacade implements IDataAccess {

    private static DAFacade instance; //Singleton instance

    UserDAO userDAO;
    TicketDAO ticketDAO;
    EventDAO eventDAO;

    private DAFacade() {
        userDAO = new UserDAO();
        ticketDAO = new TicketDAO();
        eventDAO = new EventDAO();
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
        return null;
    }
}
