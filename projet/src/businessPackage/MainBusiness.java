package businessPackage;

import modelPackage.accountModel.Account;
import exceptionPackage.account.*;
import exceptionPackage.IllegalAccountArgumentException;

import java.time.LocalDate;

//test des utilisations des classes Business et Data pour account
public class MainBusiness {
    public static void main(String[] args) {

        try {
            Account account = new Account(null, "Bhelas2", "etu52812bisbs@henallux.be", LocalDate.of(2002, 9, 18), "password", "Premiere insertion dans la BD a partir du projet java ("+LocalDate.now()+")", null,
                    true, 5, 700, "Moi");
            AccountManager accountManager = new AccountManager();

            accountManager.addAccount(account);
            System.out.println(accountManager.getAccount(9));
            System.out.println(accountManager.getAccount(account.getIdAccount()).getUsername());
            System.out.println(accountManager.getAccount("etu52812bis@henallux.be").getUsername());
            //account.setUsername("BhelasUpdated");
            account.setBio(null);
            accountManager.updateAccount(account);
            System.out.println(accountManager.getAccount("etu52812bis@henallux.be").getUsername());
            accountManager.deleteAccountLignes(account.getIdAccount(),false,true);

            for (Account account1 : accountManager.getAllAccounts()) {
                System.out.println(account1);
            }

            //connection.close( );//envoie une sql exception a mettre ou ??? a la fin de la fonction main ???
        } // par exemple try creation d'un contre => possible erreur sql ou erreur de valeur
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
