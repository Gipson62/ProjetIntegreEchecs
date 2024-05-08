package dataAccessPackage.accountDataAccess;

import modelPackage.accountModel.Account;
public interface AccountDataAccess {
    //CRUD
    void insertAccount(Account account) throws Exception;
    <T> Account selectAccount(T parameterResearch) throws Exception;
    void updateAccount(Account account) throws Exception;
    void deleteAccount(int idAccount) throws Exception;
}
