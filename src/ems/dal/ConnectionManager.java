package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

//Singleton class
public class ConnectionManager {
    private static ConnectionManager instance;
    private final SQLServerDataSource ds;

    private ConnectionManager()
    {
        ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("EMS_RAM");
        ds.setPortNumber(1433);
        ds.setUser("CSe21B_1");
        ds.setPassword("CSe21B_1");
    }

    public static ConnectionManager getInstance(){
        return instance == null ? instance = new ConnectionManager() : instance;
    }

    public static Connection getConnection() throws Exception
    {
        return getInstance().ds.getConnection();
    }
}
