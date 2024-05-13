// Utilisation pour faire des tests sur la base de données



package dataAccessPackage;

import java.sql.*;

import java.sql.Date;
import java.time.LocalDate;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;


public class AuRevoirBasile {
    public static void main(String[] args) {

        Connection connection = SingletonConnection.getInstance();
        System.out.println(connection);
        //showAllAccount(connection);
        try {
            Account account = new Account(null, "Bhelas", "etu52812@henallux.be", LocalDate.of(2002, 9, 18), "password", "Premiere insertion dans la BD a partir du projet java ("+LocalDate.now()+")", 333,
                    true, new Rank(1,"Fer","juste fer"), 700, "Moi");
            insertAccount(connection, account);
            selectAccount(connection, 9);
            //connection.close( );//envoie une sql exception a mettre ou ??? a la fin de la fonction main ???
        }
        catch (Exception exception) {
            System.out.println("1Error message: " + exception.getMessage());
            }


    }


    public static void selectAccount (Connection connection, int idAccount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
            //si rien trouver n'envoie pas une erreur a voir la valeur si null ou autre
            preparedStatement.setInt(1, idAccount);
            ResultSet resultSet = preparedStatement.executeQuery();//sqlException

            System.out.println("Account found:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Birthdate: " + resultSet.getDate("birthdate").toLocalDate());
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
        }
    }



    public static void insertAccount(Connection connection, Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO account ( username, email, birthdate, password, bio, tag, is_beginner, `rank` , gender, elo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setDate(3, Date.valueOf(account.getBirthdate()));
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setString(5, account.getBio());
            preparedStatement.setInt(6, account.getTag());
            preparedStatement.setBoolean(7, account.getIsBeginner());
            preparedStatement.setInt(8, account.getRank());
            preparedStatement.setString(9, account.getGender());
            preparedStatement.setInt(10, account.getElo());

            preparedStatement.executeUpdate();//sqlException
        }
        catch (SQLException exception) {
            System.out.println("An error occurred while inserting the account into the database.");
            //get the error message
            System.out.println("3Error message: " + exception.getMessage());

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
     }
 }
}
