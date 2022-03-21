package ems.dal.tests;
import static org.junit.Assert.*;

import ems.be.Admin;
import ems.be.EventCoordinator;
import ems.be.User;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import org.junit.Test;

public class FacadeTest {

    @Test
    public void testLogin() throws Exception {
        IDataAccess dataAccess = DAFacade.getInstance();

        User admin = dataAccess.tryLogin("admin", "admin");
        User event = dataAccess.tryLogin("event", "event");

        assertEquals(admin.getClass(), Admin.class);
        assertEquals(event.getClass(), EventCoordinator.class);
    }
}
