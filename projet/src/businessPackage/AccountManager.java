package businessPackage;

import dataAccessPackage.accountDataAccess.*;
import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;


public class AccountManager {
    AccountDataAccess dao;

    public AccountManager(){
        dao = new AccountDBAccess();
    }

    public void addAccount(Account account) throws AddAccountException {
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

}