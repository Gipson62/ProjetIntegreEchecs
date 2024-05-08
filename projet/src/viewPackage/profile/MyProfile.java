package viewPackage.profile;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;

public class MyProfile extends DefaultPanel {
    PanelManager panelManager;
    JPanel formPanel;
    JPanel buttonsPanel;
    public MyProfile(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
    }
}
