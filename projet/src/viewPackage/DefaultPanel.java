package viewPackage;

import modelPackage.accountModel.Account;

import javax.swing.*;
import java.awt.*;

public abstract class DefaultPanel extends JPanel {
    int width, height;
    /**
     * Function used to put a panel back to its original state (used everytime we switch from one panel to another
     */
    public void resetPanel() {
        return;
    }
    public void setAccount(Account account) { return;}
}
