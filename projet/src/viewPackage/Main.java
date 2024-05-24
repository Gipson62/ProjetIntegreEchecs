package viewPackage;

import controllerPackage.AccountController;
import controllerPackage.RankController;


public class Main {
    static RankController rankController = new RankController();
    static AccountController accountController = new AccountController();
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow("Applications d'échecs");
    }
}
