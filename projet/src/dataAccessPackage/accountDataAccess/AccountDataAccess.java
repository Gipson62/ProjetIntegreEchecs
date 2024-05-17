package dataAccessPackage.accountDataAccess;

import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import modelPackage.accountModel.Email;
import modelPackage.accountModel.Password;

import java.util.ArrayList;

public interface AccountDataAccess {
    //CRUD
    void addAccount(Account account) throws AddAccountException;
    <T> Account getAccount(T parameterResearch) throws ReadAccountException;
    void updateAccount(Account account) throws UpdateAccountException;
    void deleteAccountLignes(ArrayList <Integer> idAccounts) throws DeleteAccountLignesExcemption;
    ArrayList<Account> getAllAccounts() throws ReadAccountException;
    Account login(Email email, Password password) throws ReadAccountException, LoginAccountException;

}
