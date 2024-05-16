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
    JTextField email, pseudo, gender;
    JTextArea bio;
    JPasswordField password;
    EloSlider elo;
    JSpinner birthdate;
    JCheckBox beginner;
    JTable friends;
    UpdateButton updateButton;
    Account account;
    public Profiles(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.accountController = new AccountController();
        this.rankController = new RankController();
        this.init();
    }
    public void init() {
        this.setLayout(new BorderLayout());
        try {
            ArrayList<Account> allAccounts = this.accountController.getAllAccounts();
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
        /*this.updateButton = new UpdateButton("Modifier");
        this.add(updateButton, BorderLayout.SOUTH);*/
    }
    public void setAccount(Account account) {
        this.email.setText(account.getEmail());
        this.pseudo.setText(account.getUsername());
        this.password.setText(account.getPassword());
        this.elo.setValue(account.getElo());
        this.beginner.setSelected(account.getIsBeginner());
        this.gender.setText(account.getGender());
        this.bio.setText(account.getBio());
        this.account = account;
        JOptionPane.showMessageDialog(null, "Malheureusement la liste d'amis est pas encore faite", "/!\\", JOptionPane.INFORMATION_MESSAGE);
    }
    private class UpdateButton extends JButton {
        public UpdateButton(String text) {
            super(text);
            birthdate.setEnabled(false);
            elo.setEnabled(false);
            pseudo.setEnabled(false);
            password.setEnabled(false);
            email.setEnabled(false);
            beginner.setEnabled(false);
            gender.setEnabled(false);
            bio.setEnabled(false);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isEnabled = pseudo.isEnabled();
                    birthdate.setEnabled(!isEnabled);
                    elo.setEnabled(!isEnabled);
                    pseudo.setEnabled(!isEnabled);
                    password.setEnabled(!isEnabled);
                    email.setEnabled(!isEnabled);
                    beginner.setEnabled(!isEnabled);
                    gender.setEnabled(!isEnabled);
                    bio.setEnabled(!isEnabled);
                    if(isEnabled) {
                        try {
                            accountController.updateAccount(new Account(account.getIdAccount(), pseudo.getText(), email.getText(), ((java.util.Date) birthdate.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), password.getText(), bio.getText(), 8015, beginner.isSelected(), new Rank(5), elo.getValue(), gender.getText()));
                        } catch (UpdateAccountException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        updateButton.setText("Modifier");
                    } else {
                        updateButton.setText("Valider");
                    }
                }
            });
        }
    }
    @Override
    public void resetPanel() {
        this.removeAll();
        this.init();
        return;
    }
}
