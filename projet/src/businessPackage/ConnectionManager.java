package businessPackage;

import dataAccessPackage.SingletonConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public void closeConnection() {
        SingletonConnection.closeConnection();
    }
    public void databaseLogin(String password) throws SQLException {
        SingletonConnection.databaseLogin(password);
    }
    public Connection getInstance() {
        return SingletonConnection.getInstance();
    }
}
