package viewPackage.profile;

import businessPackage.AccountManager;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.LoginAccountException;
import exceptionPackage.account.ReadAccountException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.Email;
import modelPackage.accountModel.Password;
import modelPackage.accountModel.Rank;
import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends DefaultPanel {
    AccountManager accountManager;
    PanelManager panelManager;
    JPanel formPanel;
    JTextField email;
    JPasswordField password;
    JPanel buttonsPanel;
    ValidationButton validationButton;
    InscriptionButton inscriptionButton;
    public LoginPanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        JLabel title = new JLabel("Se Connecter");
        title.setFont(this.getFont().deriveFont(28f));
        this.add(title, BorderLayout.NORTH);

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

        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        this.accountManager = new AccountManager();

        this.validationButton = new ValidationButton();
        this.buttonsPanel.add(validationButton);

        inscriptionButton = new InscriptionButton();
        this.buttonsPanel.add(inscriptionButton);

        this.add(buttonsPanel, BorderLayout.SOUTH);

    }

    @Override
    public void resetPanel() {
        this.email.setText("");
        this.password.setText("");
    }
    private class InscriptionButton extends JButton {
        public InscriptionButton() {
            super("Inscription");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        panelManager.changePanel("InscriptionPanel");
                    } catch (UnknownPanel ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }

    private class ValidationButton extends JButton {
        public ValidationButton() {
            super("Valider");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        System.out.println("Login...");
                        Account loggedAccount = accountManager.login(new Email(email.getText()), new Password(password.getText()));
                        JOptionPane.showMessageDialog(null, "Successfully logged in", "Logged in", JOptionPane.INFORMATION_MESSAGE);
                        ((MyProfile) panelManager.getPanels().get("MyProfile")).setAccount(loggedAccount);
                    } catch (ReadAccountException | LoginAccountException | IllegalAccountArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
