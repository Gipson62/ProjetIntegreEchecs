package businessPackage;

import dataAccessPackage.accountDataAccess.AccountDBAccess;
import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.Email;
import modelPackage.accountModel.Password;

import java.time.LocalDate;

//test des utilisations des classes Business et Data pour account
public class MainBusiness {
    public static void main(String[] args) {

        try {

            AccountManager accountManager = new AccountManager();
            if  (accountManager.login(new Email("etu52812@henallux.be"), new Password("Kirikou1"))){
                System.out.println("Login success");
            }
            else{
                System.out.println("Login failed");
            }
            //connection.close( );//envoie une sql exception a mettre ou ??? a la fin de la fonction main ???
        } // par exemple try creation d'un contre => possible erreur sql ou erreur de valeur
        catch (Exception e) {
            System.out.println("1Error message: " + e.getMessage());
        }



    }

}
