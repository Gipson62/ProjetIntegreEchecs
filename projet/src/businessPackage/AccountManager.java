package businessPackage;

import dataAccessPackage.accountDataAccess.*;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import modelPackage.accountModel.IdAccount;
import modelPackage.accountModel.Email;

import java.util.ArrayList;

/**
 * Manages account logic and interactions with the data access.
 */
public class AccountManager {
    private AccountDataAccess dao;

    /**
     * Constructor initializes the data access object.
     */
    public AccountManager() {
        dao = new AccountDBAccess(); // Initialize with a specific implementation of AccountDataAccess
    }

    /**
     * Adds a new account if the email does not already exist in the system.
     * @param account The account to add.
     * @throws AddAccountException If the email is already in use or an error occurs during the process.
     */
    public void addAccount(Account account) throws AddAccountException {
        try {
            if (getAccount(account.getEmail()) != null) { // Check if the account with the same email already exists
                throw new AddAccountException("Email déjà existant");
            }
        } catch (ReadAccountException e) {
            throw new AddAccountException("Une erreur s'est produite");
        }
        dao.addAccount(account);
    }

    /**
     * Return an account using a flexible parameter which could be either an ID or an email.
     * @param parameterResearch The parameter to search the account by.
     * @param <T> The type of the parameter (e.g., Integer for ID, String for email).
     * @return Account The found account.
     * @throws ReadAccountException If the account cannot be found or the query parameter is invalid.
     */
    public <T> Account getAccount(T parameterResearch) throws ReadAccountException {
        try {
            if (parameterResearch instanceof Integer idAccount) {
                return dao.getAccount(new IdAccount(idAccount)); // Handle search by account ID
            } else if (parameterResearch instanceof String email) {
                return dao.getAccount(new Email(email)); // Handle search by email
            }
        } catch (IllegalAccountArgumentException e) {
            throw new ReadAccountException("Informations de recherche invalides : " + e.getMessage());
        }
        return dao.getAccount(parameterResearch); // Fallback for other types of search parameters
    }

    /**
     * Updates existing account information.
     * @param account The account to update.
     * @throws UpdateAccountException If the account does not exist or an error occurs during the update process.
     */
    public void updateAccount(Account account) throws UpdateAccountException {
        try {
            if (getAccount(account.getIdAccount()) == null) { // Verify the account exists before updating
                throw new UpdateAccountException("Compte inexistant");
            }
        } catch (ReadAccountException e) {
            throw new UpdateAccountException("Une erreur s'est produite");
        }
        dao.updateAccount(account);
    }

    /**
     * Deletes accounts based on a list of account IDs.
     * @param idAccounts List of account IDs to delete.
     * @throws DeleteAccountLignesException If an error occurs during the deletion process.
     */
    public void deleteAccountLignes(ArrayList<Integer> idAccounts) throws DeleteAccountLignesException {
        dao.deleteAccountLignes(idAccounts);
    }

    /**
     * Retun all accounts from the data store.
     * @return ArrayList<Account> List of all accounts.
     * @throws ReadAccountException If an error occurs during the retrieval process.
     */
    public ArrayList<Account> getAllAccounts() throws ReadAccountException {
        return dao.getAllAccounts();
    }
}
