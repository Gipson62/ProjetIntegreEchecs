package businessPackage;

import dataAccessPackage.accountDataAccess.*;
import modelPackage.accountModel.Account;


public class AccountManager {
    AccountDataAccess dao;

    public AccountManager(){
        dao = new AccountDBAccess();
    }

    public void insertAccount(Account account) throws Exception{
        dao.insertAccount(account);
    }

    public <T> Account selectAccount(T parameterResearch) throws Exception{
        return dao.selectAccount(parameterResearch);
    }

    public void updateAccount(Account account) throws Exception{
        dao.updateAccount(account);
    }

    public void deleteAccount(int idAccount) throws Exception{
        dao.deleteAccount(idAccount);
    }

}
