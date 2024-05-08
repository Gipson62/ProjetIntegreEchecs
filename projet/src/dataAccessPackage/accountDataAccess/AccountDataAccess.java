package dataAccessPackage.accountDataAccess;

import exceptionPackage.account.AddAccountException;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
public interface AccountDataAccess {
    //CRUD
    void addAccount(Account account) throws AddAccountException;
    <T> Account getAccount(T parameterResearch) throws ReadAccountException;
    void updateAccount(Account account) throws UpdateAccountException;
    void deleteAccountLignes(int idAccount, boolean ligne1, boolean ligne2) throws DeleteAccountLignesExcemption;
}
