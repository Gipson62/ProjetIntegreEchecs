package controllerPackage;

import businessPackage.ConnectionManager;
import dataAccessPackage.SingletonConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionController {
    private ConnectionManager connectionManager;
    public ConnectionController() {
        connectionManager = new ConnectionManager();
    }
    public void closeConnection() {
        connectionManager.closeConnection();
    }
    public void databaseLogin(String password) throws SQLException {
        connectionManager.databaseLogin(password);
    }
    public Connection getInstance() {
        return connectionManager.getInstance();
    }
}
