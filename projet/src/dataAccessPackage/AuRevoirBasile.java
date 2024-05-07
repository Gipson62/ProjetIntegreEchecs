package dataAccessPackage;

import java.sql.*;
import java.util.* ;


public class AuRevoirBasile {
    public static void main(String[] args) {

        Connection connection = SingletonConnexion.getInstance();
        System.out.println(connection);
        //showAllAccount(connection);

    }




    //Methods and fonctions test for DB
 public static void showAllAccount(Connection connection){
        //not secure
     try {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
         while (resultSet.next()) {
             System.out.println("ID: " + resultSet.getInt("id"));
             System.out.println("Username: " + resultSet.getString("username"));
             System.out.println("Email: " + resultSet.getString("email"));
             System.out.println("Birthdate: " + resultSet.getDate("birthdate"));
             System.out.println("Password: " + resultSet.getString("password"));
             System.out.println("Bio: " + resultSet.getString("bio"));
             System.out.println("Tag: " + resultSet.getInt("tag"));
             System.out.println("Is beginner: " + resultSet.getBoolean("is_beginner"));
             System.out.println("Rank: " + resultSet.getString("rank"));
             System.out.println("Elo: " + resultSet.getInt("elo"));
             System.out.println("Gender: " + resultSet.getString("gender"));
         }
     }
     catch (SQLException exception) {
         System.out.println("An error occurred while querying the database.");
         //get the error message
         System.out.println("Error message: " + exception.getMessage());
         exception.printStackTrace();
     }
 }
}
