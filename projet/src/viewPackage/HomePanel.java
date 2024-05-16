package viewPackage;

import exceptionPackage.UnknownPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends DefaultPanel {
    PanelManager panelManager;
    JPanel formPanel;
    JPanel buttonsPanel;
    public HomePanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
    }

    @Override
    public void resetPanel() {
        return;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Yo ça va les gars? ça va être rempli plus tard", 25, 25);
    }
}
