package ems.bll;

import ems.be.Ticket;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import javafx.collections.ObservableList;
import ems.bll.exceptions.DatabaseException;

import java.util.List;

public class TicketLogic {
    IDataAccess dataAccess;

    public TicketLogic() {
        dataAccess = DAFacade.getInstance();
    }

    public List<Ticket> readAllTickets() throws Exception {
        try {
            return dataAccess.readAllTickets();
        } catch (Exception e) {
            throw new DatabaseException("Could not read tickets!", e);
        }
    }
}
