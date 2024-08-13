package viewPackage;

import controllerPackage.ConnectionController;
import exceptionPackage.CantConnectToDbException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements IPanel {
    PanelManager panelManager;
    private ConnectionController connectionController;
    public HomePanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
    }

    @Override
    public void enterPanel() {
        this.init();
        return;
    }

    @Override
    public void init() {
        this.connectionController = new ConnectionController();
        JLabel text = new JLabel("<html>Application faite en Java, <br>pour le cours de Bac 2 \"Projet Intégré\", <br>donné par Mme DUBISY et <br>M. Bouraada.<br><br></html>");
        text.setEnabled(false);
        text.setFont(text.getFont().deriveFont(28f));
        this.add(text);
        JButton loginButton = new JButton("Se connecter à la base de données");
        if(connectionController.getInstance() != null) {
            loginButton.setText("Accès à la db validé.");
            loginButton.setEnabled(false);
        }
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Entrez le mot de passe :");
                JPasswordField passwordField = new JPasswordField(15);
                panel.add(label);
                panel.add(passwordField);
                String[] options = new String[]{"Ok", "Annuler"};
                int option = JOptionPane.showOptionDialog(null, panel, "Connexion bd", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    char[] password = passwordField.getPassword();
                    try {
                        connectionController.databaseLogin(String.valueOf(password));
                        loginButton.setText("Accès à la db validé.");
                        loginButton.setEnabled(false);
                    } catch (CantConnectToDbException ex) {
                        JOptionPane.showMessageDialog(null, "Le mot de passe est incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.add(loginButton);
    }
}
