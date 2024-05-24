package dataAccessPackage;

import java.sql.*;

/**
 * Manages a single database connection instance throughout the application using the singleton pattern.
 */
public class SingletonConnection {
    private static Connection connectionUnique;

    /**
     * Provides access to the single instance of the connection.
     * If the connection does not exist, it is created. If it exists, the existing connection is reused.
     *
     * @return The single shared instance of a database connection.
     */
    public static Connection getInstance()  {
        return connectionUnique;
    }

    public static void databaseLogin(String password) throws SQLException {
        try {
            connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/echecdb",
                    "testeur",
                    password
                    );
        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Closes the single database connection if it exists.
     * This method ensures that the connection is closed properly when not needed, avoiding potential resource leaks.
     */
    public static void closeConnection()  {
        if(connectionUnique != null) {
            try {
                connectionUnique.close();
            }
            catch (SQLException exception) {
                System.out.println("Une erreur s'est produite lors de la fermeture de la connexion.");
            }
            System.out.println("Connexion closed");
        }
    }
}
