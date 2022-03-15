package ems.dal.tests;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.be.User;
import ems.dal.DAFacade;
import ems.dal.IDataAccess;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class FacadeTest {
    @Test
    public void testFacade() throws SQLServerException {
        IDataAccess dataAccess = DAFacade.getInstance();

        assertNotNull(dataAccess);

        List<User> userList = dataAccess.getAllUsers();
        assertTrue(userList.size() > 0);
    }
}
