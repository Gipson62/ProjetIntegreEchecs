package viewPackage;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.account.AddAccountException;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    static RankController rankController = new RankController();
    static AccountController accountController = new AccountController();
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow("Applications d'échecs");
    }
}
