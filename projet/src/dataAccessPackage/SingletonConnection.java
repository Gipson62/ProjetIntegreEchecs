package dataAccessPackage;

import java.sql.*;

public class SingletonConnection {
    private static Connection connexionUnique;

    public static Connection getInstance( )  {
        if (connexionUnique == null) {

            try {
                connexionUnique =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/echecdb",// + "?useSSL=false"
                                "root",//  nom d’utilisateur
                                "EchecMySql*52812") ; // mot de passe

                System.out.println("Connection to the database was successful." + connexionUnique);
            }
            catch (SQLException exception) {
                System.out.println("An error occurred while connecting to the database.");
                //get the error message
                System.out.println("Error message: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return connexionUnique;
    }
}
