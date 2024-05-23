package dataAccessPackage.accountDataAccess;

import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import modelPackage.accountModel.IdAccount;

import java.util.ArrayList;

/**
 * Interface for account data access operations.
 * Defines CRUD operations for managing user accounts.
 */
public interface AccountDataAccess {
    /**
     * Adds a new account.
     * @param account The account to be added.
     * @throws AddAccountException If there is a failure in adding the account.
     */
    void addAccount(Account account) throws AddAccountException;

    /**
     * Retrieves an account based on a search parameter.
     * @param parameterResearch The search parameter for the account (can be of different types).
     * @param <T> The type can be IdAccount or Email.
     * @return The account matching the research.
     * @throws ReadAccountException If there is a failure in reading the account.
     */
    <T> Account getAccount(T parameterResearch) throws ReadAccountException;

    /**
     * Updates an existing account's information.
     * @param account The account to update.
     * @throws UpdateAccountException If there is a failure in updating the account.
     */
    void updateAccount(Account account) throws UpdateAccountException;

    /**
     * Deletes multiple accounts based on their IDs.
     * @param idAccounts List of IDs of the accounts to be deleted.
     * @throws DeleteAccountLignesException If there is a failure in deleting the accounts.
     */
    void deleteAccountLignes(ArrayList<IdAccount> idAccounts) throws DeleteAccountLignesException;

    /**
     * Retrieves all available accounts.
     * @return A list of all accounts.
     * @throws ReadAccountException If there is a failure in retrieving the accounts.
     */
    ArrayList<Account> getAllAccounts() throws ReadAccountException;

}
