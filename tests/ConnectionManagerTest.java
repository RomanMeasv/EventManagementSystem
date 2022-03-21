import ems.dal.ConnectionManager;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionManagerTest {
    @Test
    public void testGetters() throws Exception {
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        Connection connection = ConnectionManager.getConnection();

        assertNotNull(connectionManager);
        assertNotNull(connection);
    }

}
