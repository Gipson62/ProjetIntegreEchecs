package viewPackage.profile;

import businessPackage.AccountManager;
import exceptionPackage.UnknownPanel;
import exceptionPackage.account.AddAccountException;
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
import java.time.ZoneId;
import java.util.Arrays;

public class LoginPanel extends DefaultPanel {
    AccountManager accountManager;
    PanelManager panelManager;
    JPanel formPanel;
    JTextField email;
    JPasswordField password;
    JPanel buttonsPanel;
    public LoginPanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        JLabel title = new JLabel("Créer un compte");
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

        JButton validationButton = new JButton("Valider");
        this.buttonsPanel.add(validationButton);

        validationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Login...");
                    boolean isLogged = accountManager.login(new Email(email.getText()), new Password(Arrays.toString(password.getPassword())));
                    if (isLogged) {
                        JOptionPane.showMessageDialog(null, "Successfully logged in", "Logged in", JOptionPane.OK_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "There's an error in either your password or your email", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (ReadAccountException | LoginAccountException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton inscriptionButton = new JButton("Inscription");
        this.buttonsPanel.add(inscriptionButton);
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panelManager.changePanel("InscriptionPanel");
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
        this.password.setText("");
    }
}
