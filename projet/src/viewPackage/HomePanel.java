package viewPackage;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel implements IPanel {
    PanelManager panelManager;
    public HomePanel(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
    }

    @Override
    public void enterPanel() {
        return;
    }

    @Override
    public void init() {
        return;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Yo ça va les gars? ça va être rempli plus tard", 25, 25);
    }
}
