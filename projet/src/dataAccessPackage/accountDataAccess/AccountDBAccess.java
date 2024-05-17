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
import modelPackage.accountModel.Email;
import modelPackage.accountModel.Password;
import modelPackage.accountModel.Rank;
import org.mindrot.jbcrypt.BCrypt;


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
            preparedStatement.setString(4, doHashing(account.getPassword()));
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
            preparedStatement.setString(4, doHashing(account.getPassword()));
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
            String message = "Erreur lors de la modification de l'account";
            if (e.getMessage().contains("UC_tag_username")) {
                message = "Username déjà utilisé ou tag déjà utilisé.";
            } else if (e.getMessage().contains("UC_email")) {
                message = "Email déjà utilisé.";
            }
            throw new UpdateAccountException(message);
        }
    }

    @Override
    public void deleteAccountLignes(ArrayList <Integer> idAccounts) throws DeleteAccountLignesExcemption{ //, boolean deleteBio, boolean deleteGender
        for (Integer id : idAccounts) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id = ?");

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.println("Account avec l'id " + id + " à été supprimé");
            } catch (SQLException e) {
                throw new DeleteAccountLignesExcemption("Account avec l'id " + id + " n'a pas été supprimé");
                //les comptes qui n'exste pas sont quand meme affiché comme supprimé pas s'erreur si n'existe pas
            }
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
            throw new ReadAccountException("Une erreur s'est produite");
        }
    }

    @Override
    public Account login(Email email, Password password) throws ReadAccountException, LoginAccountException{
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM account WHERE email = ?");
            preparedStatement.setString(1, email.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (hashedPassword.length() < 60) {
                    throw new LoginAccountException("Password is not hashed.") ;
                }
                if (checkPassword(password.getPassword(), hashedPassword)) {
                    Account account = getAccount(email.getEmail());
                    account.setPassword(password.getPassword());
                    return account;
                }
                throw new LoginAccountException("Invalide password.");
            }
            throw new LoginAccountException("Email not found.");
        } catch (SQLException e) {
            throw new ReadAccountException(e.getMessage());
        }
    }

    // recoit un resultSet et renvoie un objet Account
    private Account resultSetToAccount(ResultSet resultSet) throws IllegalAccountArgumentException{
        try{
        return new Account(  resultSet.getInt("id"),
                                        resultSet.getString("username"),
                                        resultSet.getString("email"),
                                        resultSet.getDate("birthdate").toLocalDate(),
                                        resultSet.getString("password"),
                                        resultSet.getString("bio"),
                                        resultSet.getInt("tag"),
                                        resultSet.getBoolean("is_beginner"),
                                        new Rank(resultSet.getInt("rank")),
                                        resultSet.getInt("elo"),
                                        resultSet.getString("gender"));
        }catch (SQLException e){
            throw new IllegalAccountArgumentException(e.getMessage());
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        // Vérifie si le mot de passe saisi correspond au haché du mot de passe correct
        System.out.println(password);
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String doHashing(String password) {
        // Génère un haché bcrypt du mot de passe
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
