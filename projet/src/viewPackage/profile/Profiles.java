package viewPackage.profile;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.account.DeleteAccountLignesExcemption;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;
import modelPackage.tables.AllAccountsModel;
import viewPackage.IPanel;
import viewPackage.PanelManager;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// TODO : add a way to get written data about the user from the disk with Serializable
public class Profiles extends JPanel implements IPanel {
    private AccountController accountController;
    private RankController rankController;
    private PanelManager panelManager;
    private JTable profiles;
    private UpdateButton updateButton;
    private RemoveButton removeButton;
    private ArrayList<Account> allAccounts;
    public Profiles(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.accountController = new AccountController();
        this.rankController = new RankController();
    }
    public void init() {
        this.setLayout(new BorderLayout());
        try {
            this.allAccounts = this.accountController.getAllAccounts();
            this.profiles = new JTable(new AllAccountsModel(this.allAccounts));
            this.add(new JScrollPane(this.profiles));
        } catch (ReadAccountException e) {
            throw new RuntimeException(e);
        }
        JPanel buttonPanel = new JPanel();
        this.updateButton = new UpdateButton("Modifier");
        buttonPanel.add(updateButton, BorderLayout.EAST);
        this.removeButton = new RemoveButton("Supprimer");
        buttonPanel.add(removeButton, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    @Override
    public void enterPanel() {
        this.removeAll();
        this.init();
        return;
    }

    private class RemoveButton extends JButton {
        public RemoveButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] idAccounts = profiles.getSelectedRows();
                    ArrayList<Integer> accountsToRemove = new ArrayList<>();
                    for (int account : idAccounts) {
                        accountsToRemove.add(allAccounts.get(account).getIdAccount());
                    }
                    try {
                        System.out.println("IDs: " + accountsToRemove);
                        accountController.deleteAccountLignes(accountsToRemove);
                        enterPanel();
                    } catch (DeleteAccountLignesExcemption ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }
    private class UpdateButton extends JButton {
        public UpdateButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Account accToModify = allAccounts.get(profiles.getSelectedRow());
                    ModificationPanel modificationPanel = new ModificationPanel(panelManager);
                    modificationPanel.init();
                    modificationPanel.setAccount(accToModify);
                    panelManager.center.removeAll();
                    panelManager.center.add(modificationPanel);
                    panelManager.center.validate();
                    panelManager.center.repaint();
                }
            });
        }
    }
}
