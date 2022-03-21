package ems.dal.tests;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ems.dal.ConnectionManager;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionManagerTest {
    @Test
    public void testGetters() throws SQLServerException {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        Connection connection = ConnectionManager.getConnection();

        assertNotNull(connectionManager);
        assertNotNull(connection);
    }

}
