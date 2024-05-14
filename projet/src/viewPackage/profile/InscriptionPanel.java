package viewPackage.profile;

import controllerPackage.AccountController;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.AddAccountException;
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

public class InscriptionPanel extends DefaultPanel {
    AccountController accountController;
    PanelManager panelManager;
    JPanel formPanel, buttonsPanel, titlePanel;
    JTextField email, pseudo;
    JPasswordField password;
    JSlider elo;
    JSpinner dateSpinner;
    JCheckBox beginner;
    public InscriptionPanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.titlePanel = new JPanel();
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Créer un compte");
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
        this.elo = new JSlider(0, 3000);
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

        this.add(this.formPanel, BorderLayout.CENTER);
        this.buttonsPanel = new JPanel();

        this.accountController = new AccountController();

        JButton validationButton = new JButton("Valider");
        this.buttonsPanel.add(validationButton);

        validationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accountController.addAccount(new Account(0, pseudo.getText(), email.getText(), ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), password.getText(), "Salut c'est la bio", 8015, beginner.isSelected(), new Rank(5), elo.getValue(), "male"));
                } catch (AddAccountException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton inscriptionButton = new JButton("Se Connecter");
        this.buttonsPanel.add(inscriptionButton, BorderLayout.SOUTH);
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panelManager.changePanel("LoginPanel");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.add(buttonsPanel, BorderLayout.SOUTH);

    }
    @Override
    public void resetPanel() {
        this.email.setText("");
        this.pseudo.setText("");
        this.password.setText("");
        this.elo.setValue(500);
        this.beginner.setSelected(false);
        return;
    }
}
