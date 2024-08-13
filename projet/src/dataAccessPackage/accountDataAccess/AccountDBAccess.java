package dataAccessPackage.accountDataAccess;

import dataAccessPackage.SingletonConnection;
import modelPackage.accountModel.*;
import modelPackage.accountModel.IdAccount;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptionPackage.account.*;
import exceptionPackage.IllegalAccountArgumentException;
import org.mindrot.jbcrypt.BCrypt;


public class IAccountDBAccess implements IAccountDataAccess {
    private Connection connection;

    public IAccountDBAccess(){
        this.connection = SingletonConnection.getInstance();
    }


    @Override
    public void addAccount(Account account) throws AddAccountException{
            System.out.println("Start insertion in the DB");
        try {
            //requete pour inserer un compte dans la BD
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

            preparedStatement.executeUpdate();
            //Update the given account so its values are up to date to the db
            try {
                Account accountInfo = getAccount(new Email(account.getEmail()));
                account.setIdAccount(accountInfo.getIdAccount());
                account.setTag(accountInfo.getTag());
                account.setPassword(accountInfo.getPassword());
            } catch (IllegalAccountArgumentException | ReadAccountException e) {
                throw new AddAccountException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new AddAccountException("Erreur lors de la création du compte");
        }
    }

    @Override
    public <T> Account getAccount(T parameterResearch) throws ReadAccountException {
        try
        {
            //requete pour recuperer un compte de la BD selon l'id
            if (parameterResearch instanceof IdAccount idAccount) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
                preparedStatement.setInt(1, idAccount.getIdAccount());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    try {
                        return resultSetToAccount(resultSet);
                    } catch (IllegalAccountArgumentException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            //requete pour recuperer un compte de la BD selon l'email
            else if (parameterResearch instanceof Email email) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM account WHERE email = ?");
                preparedStatement.setString(1, email.getEmail());
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
            throw new ReadAccountException("Erreur lors de la lecture du compte");
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
            preparedStatement.setString(4, account.getPassword().contains("$2a$10$") ? account.getPassword(): doHashing(account.getPassword()));
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
            //message d'erreur selon le type d'erreur
            String message = "Erreur lors de la modification du compte";
            if (e.getMessage().contains("UC_tag_username")) {
                message = "Combinaison du pseudo et tag deja existante.";
            } else if (e.getMessage().contains("UC_email")) {
                message = "Email déjà utilisé.";
            }
            throw new UpdateAccountException(message);
        }
    }

    @Override
    public void deleteAccountLignes(ArrayList <IdAccount> idAccounts) throws DeleteAccountLignesException {
        for (IdAccount id : idAccounts) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM account WHERE id = ?");

                preparedStatement.setInt(1, id.getIdAccount());
                preparedStatement.executeUpdate();

                System.out.println("Account avec l'id " + id.getIdAccount() + " à été supprimé");
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new DeleteAccountLignesException("Account avec l'id " + id.getIdAccount() + " n'a pas été supprimé");
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
                    System.out.println("Valeur invalide dans la BD :"+e.getMessage());
                }
            }
            return accounts;
        } catch (SQLException e) {
            throw new ReadAccountException("Une erreur s'est produite lors de la lecture des comptes");
        }
    }


    /**
     * Converts a ResultSet into an Account object.
     * This method extracts account data from a SQL ResultSet obtained from a database query
     * and constructs an Account object using this data.
     *
     * @param resultSet The ResultSet from which account data is to be extracted.
     * @return Account The constructed Account object populated with data from the ResultSet.
     * @throws IllegalAccountArgumentException If any of the account fields fail validation checks.
     * @throws ReadAccountException If there is an error in reading from the ResultSet or if the data is incomplete.
     */
    private Account resultSetToAccount(ResultSet resultSet) throws IllegalAccountArgumentException,ReadAccountException{
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
            throw new ReadAccountException("Erreur lors de la lecture d'un compte");
        }
    }

    /**
     * Checks if the provided password matches the hashed password stored in the database.
     *
     * @param password Password input by the user.
     * @param hashedPassword The hashed password from the database.
     * @return boolean True if the password matches the hashed password, false otherwise.
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        // Vérifie si le mot de passe saisi correspond au haché du mot de passe correct
        System.out.println(password);
        return BCrypt.checkpw(password, hashedPassword);
    }

    /**
     * Hashes the password using the BCrypt hashing algorithm.
     * This method generates a salt automatically and applies the BCrypt hashing algorithm to the password.
     *
     * @param password The password to be hashed.
     * @return String The hashed password.
     */
    public static String doHashing(String password) {
        // Génère un haché bcrypt du mot de passe
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
