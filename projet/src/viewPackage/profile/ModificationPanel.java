package viewPackage.profile;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.AddAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Rank;
import viewPackage.DefaultPanel;
import viewPackage.EloSlider;
import viewPackage.PanelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;

public class ModificationPanel extends DefaultPanel {
    AccountController accountController;
    RankController rankController;
    PanelManager panelManager;
    JPanel formPanel, buttonsPanel, titlePanel;
    JTextField email, pseudo, gender;
    JPasswordField password;
    EloSlider elo;
    JSpinner dateSpinner;
    JCheckBox beginner;
    JTextArea bio;
    JComboBox<String> ranks;
    ValidateButton validationButton;
    public ModificationPanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.accountController = new AccountController();
        this.rankController = new RankController();
        this.setLayout(new BorderLayout());

        this.titlePanel = new JPanel();
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Modifier un compte");
        title.setFont(this.getFont().deriveFont(28f));
        this.titlePanel.add(title, BorderLayout.CENTER);
        this.add(this.titlePanel, BorderLayout.NORTH);

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        JLabel emailLabel = new JLabel("Email :");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        gridBag.setConstraints(emailLabel, c);
        this.formPanel.add(emailLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.email = new JTextField();
        gridBag.setConstraints(this.email, c);
        this.formPanel.add(this.email);

        JLabel passwordLabel = new JLabel("Mot de Passe :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(passwordLabel, c);
        this.formPanel.add(passwordLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.password = new JPasswordField();
        gridBag.setConstraints(this.password, c);
        this.formPanel.add(this.password);

        JLabel pseudoLabel = new JLabel("Pseudo :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(pseudoLabel, c);
        this.formPanel.add(pseudoLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.pseudo = new JTextField();
        gridBag.setConstraints(this.pseudo, c);
        this.formPanel.add(this.pseudo);

        JLabel eloLabel = new JLabel("Elo :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(eloLabel, c);
        this.formPanel.add(eloLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.elo = new EloSlider(eloLabel);
        gridBag.setConstraints(this.elo, c);
        this.formPanel.add(this.elo);

        JLabel dateLabel = new JLabel("Date de naissance :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(dateLabel, c);
        this.formPanel.add(dateLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        this.dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, model.toPattern()));
        gridBag.setConstraints(this.dateSpinner, c);
        this.formPanel.add(this.dateSpinner);

        JLabel beginnerLabel = new JLabel("Débutant :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(beginnerLabel, c);
        this.formPanel.add(beginnerLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.beginner = new JCheckBox();
        gridBag.setConstraints(this.beginner, c);
        this.formPanel.add(this.beginner);

        JLabel genderLabel = new JLabel("Genre");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(genderLabel, c);
        this.formPanel.add(genderLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.gender = new JTextField();
        gridBag.setConstraints(this.gender, c);
        this.formPanel.add(this.gender);

        JLabel bioLabel = new JLabel("Bio");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(bioLabel, c);
        this.formPanel.add(bioLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.bio = new JTextArea();
        gridBag.setConstraints(this.bio, c);
        this.formPanel.add(this.bio);

        JLabel rankLabel = new JLabel("Rank");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(rankLabel, c);
        this.formPanel.add(rankLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        try {
            ArrayList<Rank> allRanks = rankController.getAllRanks();
            String[] rankNames = new String[allRanks.size()];
            for(int i = 0; i < allRanks.size(); i++) {
                rankNames[i] = allRanks.get(i).getName();
            }
            this.ranks = new JComboBox<String>(rankNames);
        } catch (ReadRankException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        gridBag.setConstraints(this.ranks, c);
        this.formPanel.add(this.ranks);

        this.add(this.formPanel, BorderLayout.CENTER);
        this.buttonsPanel = new JPanel();

        this.validationButton = new ValidateButton();
        this.buttonsPanel.add(validationButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);

    }
    @Override
    public void resetPanel() {
        this.email.setText("");
        this.pseudo.setText("");
        this.password.setText("");
        this.gender.setText("");
        this.bio.setText("");
        this.elo.setValue(500);
        this.beginner.setSelected(false);
        return;
    }
    public void setAccount(Account account) {
        //TODO
        return;
    }
    private class ValidateButton extends JButton {
        public ValidateButton() {
            super("Valider");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        LocalDate date = ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        ArrayList<Rank> allRanks = rankController.getAllRanks();
                        Rank rank = null;
                        for (int i = 0; rank == null && i < allRanks.size(); i++) {
                            Rank currRank = allRanks.get(i);
                            if (Objects.equals((String) ranks.getSelectedItem(), currRank.getName())) {
                                rank = currRank;
                                System.out.println(currRank);
                            }
                        }
                        if (rank != null) {
                            accountController.addAccount(new Account(null, pseudo.getText(), email.getText(), date, password.getText(), bio.getText(), null, beginner.isSelected(), rank, elo.getValue(), gender.getText()));
                        } else {
                            JOptionPane.showMessageDialog(null, "You have to select an existing rank", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        panelManager.changePanel("Profiles");
                    } catch (AddAccountException | UnknownPanel | IllegalAccountArgumentException | ReadRankException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
