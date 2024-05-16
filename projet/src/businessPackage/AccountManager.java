package businessPackage;

import dataAccessPackage.accountDataAccess.*;
import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import modelPackage.accountModel.Password;
import modelPackage.accountModel.Email;

import java.util.ArrayList;


public class AccountManager {
    AccountDataAccess dao;

    public AccountManager(){
        dao = new AccountDBAccess();
    }

    public void addAccount(Account account) throws AddAccountException{
        try {
            if (getAccount(account.getEmail()) != null)
                throw new AddAccountException("Email deja existant");
        } catch (ReadAccountException e) {
            throw new AddAccountException("Une erreur s'est produite");
        }
        dao.addAccount(account);
    }

    public <T> Account getAccount(T parameterResearch) throws ReadAccountException{
        return dao.getAccount(parameterResearch);
    }

    public void updateAccount(Account account) throws UpdateAccountException{
        dao.updateAccount(account);
    }

    public void deleteAccountLignes(int idAccount, boolean deleteBio, boolean deleteGender) throws DeleteAccountLignesExcemption{
        dao.deleteAccountLignes(idAccount, deleteBio, deleteGender);
    }

    public ArrayList<Account> getAllAccounts() throws ReadAccountException{
        return dao.getAllAccounts();
    }

    public Account login(Email email, Password password) throws ReadAccountException, LoginAccountException{
        return dao.login(email, password);
    }
}
