package dataAccessPackage.accountDataAccess;

import dataAccessPackage.SingletonConnection;
import modelPackage.accountModel.Account;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptionPackage.account.*;
import exceptionPackage.IllegalAccountArgumentException;




public class AccountDBAccess implements AccountDataAccess{
    private Connection connection;

    public AccountDBAccess(){
        this.connection = SingletonConnection.getInstance();
    }


    @Override
    public void addAccount(Account account) throws AddAccountException{
        //créé un nouvel account dans la BD si valeur ok et non deja existante pour email et username+tag et id
        //Puis enregistre l'id de l'account dans l'objet account passé en paramètre par adresse
//TODO : ajouter une exception pour les erreurs sql et un save point + rollback (si erreur generateur id est accrémenté)
            System.out.println("Start insertion in the DB");
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Account (username, email, birthdate, password, bio, tag, is_beginner, `rank`, gender, elo)  \n" +
                            "SELECT ?, ?, ?, ?, ?, \n" +
                            "       IFNULL(MAX(tag), 0) + 1, ?, ?, ?, ?\n" +
                            "FROM Account\n" +
                            "WHERE username = ?;");

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setDate(3, Date.valueOf(account.getBirthdate()));
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setString(5, account.getBio());

            preparedStatement.setBoolean(6, account.getIsBeginner());
            preparedStatement.setInt(7, account.getRank());
            preparedStatement.setString(8, account.getGender());
            preparedStatement.setInt(9, account.getElo());
            preparedStatement.setString(10, account.getUsername());

            preparedStatement.executeUpdate();//sqlException
            try {
                account.setIdAccount( getAccount(account.getEmail()).getIdAccount());
                account.setTag(getAccount(account.getEmail()).getTag());
            } catch (IllegalAccountArgumentException | ReadAccountException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {

            throw new AddAccountException(e.getMessage());// ici créé new exception perso
        }
    }

    @Override
    public <T> Account getAccount(T parameterResearch) throws ReadAccountException {

        try
        {
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
            } else if (parameterResearch instanceof String && ((String) parameterResearch).contains("@")) {
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
            return null;
        }catch (SQLException e){
            throw new ReadAccountException(e.getMessage());
        }
    }


    @Override
    public void updateAccount(Account account) throws UpdateAccountException {
        try
        {
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
        catch (SQLException e)
        {
            throw new UpdateAccountException(e.getMessage());
        }
    }

    @Override
    //boolean pour savoir si on supprime la bio et ou le genre
    public void deleteAccountLignes(int idAccount, boolean deleteBio, boolean deleteGender) throws DeleteAccountLignesExcemption{ //, boolean deleteBio, boolean deleteGender
        if (!deleteBio && !deleteGender) {
            throw new DeleteAccountLignesExcemption("You must delete at least one column.");
        }
        try {
            String nullLignes = deleteBio ? "bio = NULL" : "";
            nullLignes += deleteGender ? (nullLignes.isEmpty() ?"gender = NULL": ",gender = NULL") : "";
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET "+ nullLignes +" WHERE id = ?");
            //delet account
            //PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id = ?");
            preparedStatement.setInt(1, idAccount);
            preparedStatement.executeUpdate();
            System.out.println("Lignes deleted from the database.");
        } catch (SQLException e) {
            throw new DeleteAccountLignesExcemption(e.getMessage());
        }
    }


    @Override
    public ArrayList<Account> getAllAccounts() throws ReadAccountException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                try {
                    accounts.add(resultSetToAccount(resultSet));
                } catch (IllegalAccountArgumentException e) {
                    e.printStackTrace();
                }
            }
            return accounts;
        } catch (SQLException e) {
            throw new ReadAccountException(e.getMessage());
        }
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
