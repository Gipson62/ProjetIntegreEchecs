package viewPackage.profile;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.DeleteAccountLignesExcemption;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;
import viewPackage.IPanel;
import viewPackage.PanelManager;

import javax.swing.*;
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
            String[][] data = new String[allAccounts.size()][10];
            for(int i = 0; i < allAccounts.size(); i++) {
                Account currAccount = allAccounts.get(i);
                data[i][0] = currAccount.getEmail();
                data[i][1] = currAccount.getUsername();
                Rank rank = this.rankController.getRankById(currAccount.getRank());
                if (rank != null) {
                    data[i][2] = rank.getName();
                } else {
                    JOptionPane.showMessageDialog(null, "Le rang pour l'utilisateur \"" + currAccount.getEmail() + "\" n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                data[i][3] = currAccount.getBirthdate().toString();
                data[i][4] = currAccount.getIsBeginner().toString();
                data[i][5] = String.valueOf(currAccount.getElo());
                data[i][6] = currAccount.getPassword();
                data[i][7] = currAccount.getBio();
                data[i][8] = String.valueOf(currAccount.getTag());
                data[i][9] = currAccount.getGender();
            }
            String[] columnNames = {"Email", "Username", "Rank", "Birthdate", "IsBeginner", "Elo", "Password", "Bio", "Tag", "Gender"};
            this.profiles = new JTable(data, columnNames);
            this.add(new JScrollPane(this.profiles));
        } catch (ReadAccountException | ReadRankException e) {
            throw new RuntimeException(e);
        }
        JPanel buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BorderLayout());
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
                    modificationPanel.setAccount(accToModify);
                    panelManager.center.removeAll();
                    panelManager.center.add(modificationPanel);
                    panelManager.center.validate();
                    panelManager.center.repaint();
                    try {
                        panelManager.changePanel("ModificationPanel");
                    } catch (UnknownPanel ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }
}
