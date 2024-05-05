package viewPackage.profile;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InscriptionPanel extends DefaultPanel {
    MainWindow mainWindow;
    JPanel formPanel;
    JPanel buttonsPanel;
    public InscriptionPanel(MainWindow initMainWindow) {
        this.mainWindow = initMainWindow;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setSize(150, 150);
        GridLayout gridLayout = new GridLayout(6, 2, 5, 5);
        this.formPanel.setLayout(gridLayout);

        this.formPanel.add(new JLabel("Email"));
        JTextField emailTextField = new JTextField();
        this.formPanel.add("email", emailTextField);

        this.formPanel.add(new JLabel("Mot de passe"));
        JPasswordField passwordField = new JPasswordField();
        this.formPanel.add("mdpField", passwordField);

        this.formPanel.add(new JLabel("pseudo"));
        JTextField pseudoTextField = new JTextField();
        this.formPanel.add("pseudo", pseudoTextField);

        this.formPanel.add(new JLabel("elo"));
        JSlider eloSlider = new JSlider(0, 3000);
        this.formPanel.add(eloSlider);

        this.formPanel.add(new JLabel("date"));
        JSpinner dateSpinner = new JSpinner();
        this.formPanel.add(dateSpinner);

        this.formPanel.add(new JLabel("débutant"));
        JCheckBox beginnerCheckBox = new JCheckBox();
        this.formPanel.add(beginnerCheckBox);


        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(1, 2, 5, 5);
        this.buttonsPanel.setLayout(gridLayout1);
        this.buttonsPanel.add(new JButton("Valider"));
        JButton inscriptionButton = new JButton("Se Connecter");
        this.buttonsPanel.add(inscriptionButton, BorderLayout.SOUTH);
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.changePanel("LoginPanel");
            }
        });
        this.add(buttonsPanel, BorderLayout.SOUTH);

    }
    @Override
    public void resetPanel() {
        return;
    }
}
