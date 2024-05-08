package dataAccessPackage.accountDataAccess;

import dataAccessPackage.SingletonConnection;
import modelPackage.accountModel.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import exceptionPackage.IllegalAccountArgumentException;


public class AccountDBAccess implements AccountDataAccess{
    private Connection connection;

    public AccountDBAccess(){
        this.connection = SingletonConnection.getInstance();
    }
    

    @Override
    public void insertAccount(Account account) throws SQLException{
        //créé un nouvel account dans la BD si valeur ok et non deja existante pour email et username+tag et id
        //Puis enregistre l'id de l'account dans l'objet account passé en paramètre par adresse
//TODO : ajouter une exception pour les erreurs sql et un save point + rollback (si erreur generateur id est accrémenté)
            System.out.println("Start insertion in the DB\n");

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO account ( username, email, birthdate, password, bio, tag, is_beginner, `rank` , gender, elo) " +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
            try {
                account.setIdAccount( selectAccount(account.getEmail()).getIdAccount());
            } catch (IllegalAccountArgumentException e) {
                e.printStackTrace();
            }
    }

    @Override
    public <T> Account selectAccount(T parameterResearch) throws SQLException {

        if (parameterResearch instanceof Integer) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
        preparedStatement.setInt(1, (int) parameterResearch);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            try {
                return resultSetToAccount(resultSet);
            } catch (IllegalAccountArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }else if (parameterResearch instanceof String && ((String) parameterResearch).contains("@")) {
            String email = (String) parameterResearch;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                try {
                    return resultSetToAccount(resultSet);
                } catch (IllegalAccountArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        // Add more conditions here for other types of parameters

        return null;
    }


    @Override
    public void updateAccount(Account account) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE account SET username = ?, email = ?, birthdate = ?, password = ?, bio = ?, tag = ?, is_beginner = ?, `rank` = ?, gender = ?, elo = ? WHERE id = ?");

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
        preparedStatement.setInt(11, account.getIdAccount());

        preparedStatement.executeUpdate();
    }

    @Override
    //boolean pour savoir si on supprime la bio et ou le genre
    public void deleteAccountLignes(int idAccount, boolean deleteBio, boolean deleteGender) { //, boolean deleteBio, boolean deleteGender
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET bio = NULL, gender = NULL WHERE id = ?");
            //delet account
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id = ?");
            preparedStatement.setInt(1, idAccount);
            preparedStatement.executeUpdate();
            System.out.println("Account deleted from the database.");
        } catch (SQLException exception) {
            System.out.println("An error occurred while deleting the account from the database.");
            //get the error message
            System.out.println("Error message: " + exception.getMessage());
        }
        //suppression dans la BD

    }

    // recoit un resultSet et renvoie un objet Account
    private Account resultSetToAccount(ResultSet resultSet) throws SQLException, IllegalAccountArgumentException{
        return new Account(  resultSet.getInt("id"),
                                        resultSet.getString("username"),
                                        resultSet.getString("email"),
                                        resultSet.getDate("birthdate").toLocalDate(),
                                        resultSet.getString("password"),
                                        resultSet.getString("bio"),
                                        resultSet.getInt("tag"),
                                        resultSet.getBoolean("is_beginner"),
                                        resultSet.getInt("rank"),
                                        resultSet.getInt("elo"),
                                        resultSet.getString("gender"));
    }

}
