package controllerPackage;

import businessPackage.AccountManager;
import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import modelPackage.accountModel.Password;
import modelPackage.accountModel.Email;

import java.util.ArrayList;


public class AccountController {
    AccountManager accountManager;

    public AccountController(){
        accountManager = new AccountManager();
    }

    public void addAccount(Account account) throws AddAccountException {
        accountManager.addAccount(account);
    }

    public <T> Account getAccount(T parameterResearch) throws ReadAccountException{
        return accountManager.getAccount(parameterResearch);
    }

    public void updateAccount(Account account) throws UpdateAccountException{
        accountManager.updateAccount(account);
    }

    public void deleteAccountLignes(int idAccount, boolean deleteBio, boolean deleteGender) throws DeleteAccountLignesExcemption{
        accountManager.deleteAccountLignes(idAccount, deleteBio, deleteGender);
    }

    public ArrayList<Account> getAllAccounts() throws ReadAccountException{
        return accountManager.getAllAccounts();
    }

    public Account login(Email email, Password password) throws ReadAccountException, LoginAccountException{
        return accountManager.login(email, password);
    }
}
