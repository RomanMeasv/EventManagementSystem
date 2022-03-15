package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.User;

import java.util.List;

public class DAFacade implements IDataAccess {

    private static DAFacade instance; //Singleton instance

    UserDAO userDAO;
    TicketDAO ticketDAO;
    EventDAO eventDAO;

    private DAFacade() throws SQLServerException {
        userDAO = new UserDAO();
        ticketDAO = new TicketDAO();
        eventDAO = new EventDAO();
    }

    public static DAFacade getInstance() throws SQLServerException {
        return instance == null ? instance = new DAFacade() : instance;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }
}
