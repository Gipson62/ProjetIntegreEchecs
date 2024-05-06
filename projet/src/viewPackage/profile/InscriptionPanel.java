package viewPackage.profile;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class InscriptionPanel extends DefaultPanel {
    MainWindow mainWindow;
    JPanel formPanel, buttonsPanel;
    JTextField email, pseudo;
    JPasswordField password;
    JSlider elo;
    JSpinner date;
    JCheckBox beginner;
    public InscriptionPanel(MainWindow initMainWindow) {
        this.mainWindow = initMainWindow;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setSize(150, 150);
        GridLayout gridLayout = new GridLayout(6, 2, 5, 5);
        this.formPanel.setLayout(gridLayout);

        this.formPanel.add(new JLabel("Email"));
        this.email = new JTextField();
        this.formPanel.add("email", this.email);

        this.formPanel.add(new JLabel("Mot de passe"));
        this.password = new JPasswordField();
        this.formPanel.add("mdpField", this.password);

        this.formPanel.add(new JLabel("pseudo"));
        this.pseudo = new JTextField();
        this.formPanel.add("pseudo", this.pseudo);

        this.formPanel.add(new JLabel("elo"));
        this.elo = new JSlider(0, 3000);
        this.formPanel.add(this.elo);

        this.formPanel.add(new JLabel("date"));
        this.date = new JSpinner();
        this.formPanel.add(this.date);

        this.formPanel.add(new JLabel("débutant"));
        this.beginner = new JCheckBox();
        this.formPanel.add(this.beginner);


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
        this.email.setText("");
        this.pseudo.setText("");
        this.password.setText("");
        this.elo.setValue(500);
        this.beginner.setSelected(false);
        //this.date.setValue(new Date(1970, Calendar.JANUARY, 1));
        return;
    }
}
