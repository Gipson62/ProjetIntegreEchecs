package controllerPackage;

import businessPackage.ConnectionManager;

public class ConnectionController {
    private ConnectionManager connectionManager;
    public ConnectionController() {
        connectionManager = new ConnectionManager();
    }
    public void closeConnection() {
        connectionManager.closeConnection();
    }
}
