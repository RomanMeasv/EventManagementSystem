package ems.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

//Singleton class
public class ConnectionManager {
    private static ConnectionManager cm;
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
        return cm == null ? cm = new ConnectionManager() : cm;
    }

    public static Connection getConnection() throws SQLServerException
    {
        return getInstance().ds.getConnection();
    }
}
