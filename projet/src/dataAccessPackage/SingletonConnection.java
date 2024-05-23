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
        if (connectionUnique == null) {

            try {
                connectionUnique =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/echecdb",// + "?useSSL=false"
                                "root",//  nom d’utilisateur
                                "EchecMySql*52812") ; // mot de passe

                System.out.println("Connection to the database was successful." + connectionUnique);
            }
            catch (SQLException exception) {
                System.out.println("An error occurred while connecting to the database.");
                //get the error message
                System.out.println("Error message: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return connectionUnique;
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
