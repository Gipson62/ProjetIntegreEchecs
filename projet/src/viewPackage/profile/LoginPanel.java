package viewPackage.profile;

import exceptionPackage.UnknownPanel;
import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends DefaultPanel {
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
        this.buttonsPanel.add(new JButton("Valider"));
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
