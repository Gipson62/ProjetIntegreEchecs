package businessPackage;

import modelPackage.accountModel.Account;

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
            accountManager.deleteAccount(account.getIdAccount());

            //connection.close( );//envoie une sql exception a mettre ou ??? a la fin de la fonction main ???
        }
        catch (Exception exception) {
            System.out.println("1Error message: " + exception.getMessage());
        }

    }
}
