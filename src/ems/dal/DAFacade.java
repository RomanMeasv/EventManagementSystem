package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
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
}
