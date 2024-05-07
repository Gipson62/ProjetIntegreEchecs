// Utilisation pour faire des tests sur la base de données



package dataAccessPackage;

import java.sql.*;
import dataAccessPackage.SingletonConnexion;

import java.sql.Date;
import java.util.* ;
import java.time.LocalDate;
import modelPackage.accountModel.*;
//import modelPackage.accountModel.Account;



public class AuRevoirBasile {
    public static void main(String[] args) {

        Connection connection = SingletonConnexion.getInstance();
        System.out.println(connection);
        //showAllAccount(connection);
        try {
            Account account = new Account(null, "Bhelas", "etu52812@henallux.be", LocalDate.of(2002, 9, 18), "password", "Premiere insertion dans la BD a partir du projet java ("+LocalDate.now()+")", 333,
                    true, 5, 700, "Moi");
            //insertAccount(connection, account);
            selectAccount(connection, 10);
        }
        catch (Exception exception) {
            System.out.println("1Error message: " + exception.getMessage());
            }

    }


    public static void selectAccount (Connection connection, int idAccount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
            preparedStatement.setInt(1, idAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            }
        }
        catch (SQLException exception) {
            System.out.println("An error occurred while querying the database.");
            //get the error message
            System.out.println("2Error message: " + exception.getMessage());
            exception.printStackTrace();
        }
    }



    public static void insertAccount(Connection connection, Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account (id, username, email, birthdate, password, bio, tag, is_beginner, `rank` , gender, elo) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setDate(4, Date.valueOf(account.getBirthdate()));
            preparedStatement.setString(5, account.getPassword());
            preparedStatement.setString(6, account.getBio());
            preparedStatement.setInt(7, account.getTag());
            preparedStatement.setBoolean(8, account.getIsBeginner());
            preparedStatement.setInt(9, account.getRank());
            preparedStatement.setString(10, account.getGender());
            preparedStatement.setInt(11, account.getElo());

            preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            System.out.println("An error occurred while inserting the account into the database.");
            //get the error message
            System.out.println("3Error message: " + exception.getMessage());
            exception.printStackTrace();
        }
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
         System.out.println("4Error message: " + exception.getMessage());
         exception.printStackTrace();
     }
 }
}
