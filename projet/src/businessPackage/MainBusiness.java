package businessPackage;

import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import exceptionPackage.IllegalAccountArgumentException;

import java.time.LocalDate;

//test des utilisations des classes Business
public class MainBusiness {
    public static void main(String[] args) {

        try {
            Account account = new Account(null, "Bhelas2", "etu52812bis@henallux.be", LocalDate.of(2002, 9, 18), "password", "Premiere insertion dans la BD a partir du projet java ("+LocalDate.now()+")", 333,
                    true, 5, 700, "Moi");
            AccountManager accountManager = new AccountManager();

            accountManager.insertAccount(account);
            System.out.println(accountManager.selectAccount(9));
            System.out.println(accountManager.selectAccount(account.getIdAccount()).getUsername());
            System.out.println(accountManager.selectAccount("etu52812bis@henallux.be").getUsername());
            account.setUsername("BhelasUpdated");
            accountManager.updateAccount(account);
            System.out.println(accountManager.selectAccount("etu52812bis@henallux.be").getUsername());
            accountManager.deleteAccountLignes(account.getIdAccount(),true,false);


            //connection.close( );//envoie une sql exception a mettre ou ??? a la fin de la fonction main ???
        }
        catch (IllegalAccountArgumentException e) {
            System.out.println("1Error message: " + e.getMessage());
        }
        catch (AddAccountException e) {
            System.out.println("2Error message: " + e.getMessage());
        }
        catch (ReadAccountException e) {
            System.out.println("3Error message: " + e.getMessage());
        }
        catch (UpdateAccountException e) {
            System.out.println("4Error message: " + e.getMessage());
        }
        catch (DeleteAccountLignesExcemption e) {
            System.out.println("5Error message: " + e.getMessage());
        }


    }

}
