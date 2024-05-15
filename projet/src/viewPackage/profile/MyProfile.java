package viewPackage.profile;

import businessPackage.AccountManager;
import controllerPackage.AccountController;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.UpdateAccountException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;
import viewPackage.DefaultPanel;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Arrays;

// TODO : add a way to get written data about the user from the disk with Serializable
public class MyProfile extends DefaultPanel {
    AccountController accountController;
    PanelManager panelManager;
    JPanel infoPanel, friendsPanel, buttonsPanel;
    JTextField email, pseudo, gender;
    JTextArea bio;
    JPasswordField password;
    JSlider elo;
    JSpinner birthdate;
    JCheckBox beginner;
    JTable friends;
    UpdateButton updateButton;
    Account account;
    public MyProfile(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.infoPanel = new JPanel();
        this.setLayout(new BorderLayout());
        GridBagLayout infoGridBag = new GridBagLayout();
        GridBagConstraints infoC = new GridBagConstraints();
        this.infoPanel.setLayout(infoGridBag);

        JLabel pseudoLabel = new JLabel("Pseudo :");
        infoC.fill = GridBagConstraints.BOTH;
        infoC.weightx = 1.0;
        infoGridBag.setConstraints(pseudoLabel, infoC);
        this.infoPanel.add(pseudoLabel);
        this.pseudo = new JTextField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.pseudo, infoC);
        this.infoPanel.add(this.pseudo);

        JLabel eloLabel = new JLabel("Elo :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(eloLabel, infoC);
        this.infoPanel.add(eloLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        this.elo = new JSlider(0, 3000);
        infoGridBag.setConstraints(this.elo, infoC);
        this.infoPanel.add(this.elo);

        JLabel emailLabel = new JLabel("Email :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(emailLabel, infoC);
        this.infoPanel.add(emailLabel);
        this.email = new JTextField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.email, infoC);
        this.infoPanel.add(this.email);

        JLabel beginnerLabel = new JLabel("Débutant :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(beginnerLabel, infoC);
        this.infoPanel.add(beginnerLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        this.beginner = new JCheckBox();
        infoGridBag.setConstraints(this.beginner, infoC);
        this.infoPanel.add(this.beginner);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(passwordLabel, infoC);
        this.infoPanel.add(passwordLabel);
        this.password = new JPasswordField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.password, infoC);
        this.infoPanel.add(this.password);

        JLabel dateLabel = new JLabel("Date de naissance :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(dateLabel, infoC);
        this.infoPanel.add(dateLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        this.birthdate = new JSpinner(new SpinnerDateModel());
        birthdate.setEditor(new JSpinner.DateEditor(birthdate, model.toPattern()));
        infoGridBag.setConstraints(this.birthdate, infoC);
        this.infoPanel.add(this.birthdate);

        JLabel genderLabel = new JLabel("Genre :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(genderLabel, infoC);
        this.infoPanel.add(genderLabel);
        this.gender = new JTextField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.gender, infoC);
        this.infoPanel.add(this.gender);

        JLabel bioLabel = new JLabel("Bio :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(bioLabel, infoC);
        this.infoPanel.add(bioLabel);
        this.bio = new JTextArea();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.bio, infoC);
        this.infoPanel.add(this.bio);

        this.friendsPanel = new JPanel();
        Object[][] data = new Object[4][4];
        String[] columnNames = { "Pseudo#Tag", "Email", "Elo", "Ajouter?" };
        for (int i = 0; i < 4; i++) {
            data[i][0] = "Name#" + i;
            data[i][1] = "Email@email.salut";
            data[i][2] = "elo:" + i;
            data[i][3] = new JButton("Ajouter");
        }
        this.friends = new JTable(data, columnNames);
        this.friendsPanel.setLayout(new BorderLayout());
        this.friendsPanel.add(this.friends, BorderLayout.CENTER);
        JButton addButton = new JButton("Ajouter");
        this.friendsPanel.add(addButton, BorderLayout.SOUTH);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(friends.getSelectionModel().isSelectionEmpty()) {
                    System.out.println("Nothing is selected");
                } else {
                    System.out.println(Arrays.toString(friends.getSelectionModel().getSelectedIndices()));
                }
            }
        });

        this.buttonsPanel = new JPanel();
        JButton removeButton = new JButton("Supprimer");

        this.buttonsPanel.add(removeButton);
        this.updateButton = new UpdateButton("Modifier");
        this.buttonsPanel.add(updateButton);

        this.add(this.infoPanel, BorderLayout.NORTH);
        this.add(this.friendsPanel, BorderLayout.CENTER);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);

        this.accountController = new AccountController();
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
        //Here it's considered as the function to enter this panel
        if (account == null) {
            JOptionPane.showMessageDialog(null, "Bro you aren't logged in yet", "Erreur", JOptionPane.ERROR_MESSAGE);
            try {
                panelManager.changePanel("LoginPanel");
            } catch (UnknownPanel ex) {
                System.out.println(ex.getMessage());
            }
        }
        return;
    }
}
