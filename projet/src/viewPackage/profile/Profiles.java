package viewPackage.profile;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.account.UpdateAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;
import viewPackage.DefaultPanel;
import viewPackage.EloSlider;
import viewPackage.PanelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

// TODO : add a way to get written data about the user from the disk with Serializable
public class Profiles extends DefaultPanel {
    AccountController accountController;
    RankController rankController;
    PanelManager panelManager;
    JTable friends;
    UpdateButton updateButton;
    ArrayList<Account> allAccounts;
    public Profiles(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.accountController = new AccountController();
        this.rankController = new RankController();
        this.init();
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
            this.friends = new JTable(data, columnNames);
            this.add(new JScrollPane(this.friends));
        } catch (ReadAccountException | ReadRankException e) {
            throw new RuntimeException(e);
        }
        this.updateButton = new UpdateButton("Modifier");
        this.add(updateButton, BorderLayout.SOUTH);
    }
    @Override
    public void resetPanel() {
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
                    panelManager.getPanels().get("ModificationPanel").setAccount(allAccounts.get(friends.getSelectedRow()));
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
