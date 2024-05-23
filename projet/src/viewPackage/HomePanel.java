package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements IPanel {
    PanelManager panelManager;
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
        JLabel text = new JLabel("<html>Application faite en Java, <br>pour le cours de Bac 2 \"Projet Intégré\", <br>donné par Mme DUBISY et <br>M. Bouraada.<br><br></html>");
        text.setEnabled(false);
        text.setFont(text.getFont().deriveFont(28f));
        this.add(text);
        JButton profilesButton = new JButton("Aller à la page profil");
        profilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("Profiles");
            }
        });
        this.add(profilesButton);
        return;
    }
}
