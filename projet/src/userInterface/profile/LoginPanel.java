package userInterface.profile;

import userInterface.DefaultPanel;
import userInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends DefaultPanel {
    MainWindow mainWindow;
    JPanel formPanel;
    JPanel buttonsPanel;
    public LoginPanel(MainWindow initMainWindow) {
        this.mainWindow = initMainWindow;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2, 5, 5);
        gridLayout.preferredLayoutSize(this.formPanel);
        this.formPanel.setLayout(gridLayout);
        this.formPanel.add(new JLabel("Email"));
        JTextField emailTextField = new JTextField();
        this.formPanel.add("email", emailTextField);
        this.formPanel.add(new JLabel("Mot de passe"));
        JPasswordField passwordField = new JPasswordField();
        this.formPanel.add("mdpField", passwordField);
        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(1, 2, 5, 5);
        this.buttonsPanel.add(new JButton("Valider"));
        JButton inscriptionButton = new JButton("Inscription");
        this.buttonsPanel.add(inscriptionButton);
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.changePanel("InscriptionPanel");
            }
        });
        this.add(buttonsPanel, BorderLayout.SOUTH);

    }

    @Override
    public void resetPanel() {
        return;
    }
}
