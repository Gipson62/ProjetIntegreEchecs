package viewPackage;

import exceptionPackage.UnknownPanel;
import viewPackage.profile.InscriptionPanel;
import viewPackage.profile.LoginPanel;
import viewPackage.profile.MyProfile;
import viewPackage.searches.EloSearch;
import viewPackage.searches.FriendTournamentsSearch;
import viewPackage.searches.TournamentsSearch;
import viewPackage.stats.OpeningsStats;
import viewPackage.stats.WinratePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class PanelManager extends JPanel {
    private JPanel left, right;
    private JPanel center;
    private MainWindow mainWindow;
    private HashMap<String, DefaultPanel> panels;
    private CardLayout cardLayout;
    public PanelManager(MainWindow mainWindow) {
        super(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.mainWindow = mainWindow;
        PanelListener listener = new PanelListener(this);
        this.addComponentListener(listener);
        this.left = createLeftPanel();
        this.right = createRightPanel();
        this.center = createCenterPanel();

        this.add(this.left);
        this.add(this.center);
        this.add(this.right);

        this.cardLayout = new CardLayout();
        this.center.setLayout(cardLayout);

        this.panels = new HashMap<>();

        this.addPanel("HomePanel", new HomePanel(this));
        this.addPanel("LoginPanel", new LoginPanel(this));
        this.addPanel("InscriptionPanel", new InscriptionPanel(this));
        this.addPanel("EloSearch", new EloSearch(this));
        this.addPanel("TournamentsSearch", new TournamentsSearch(this));
        this.addPanel("FriendTournamentsSearch", new FriendTournamentsSearch(this));
        this.addPanel("MyProfile", new MyProfile(this));
        this.addPanel("OpeningsStats", new OpeningsStats(this));
        this.addPanel("WinratePanel", new WinratePanel(this));
        this.cardLayout.show(this.center, "HomePanel");
        System.out.println(this);
    }

    public JPanel createLeftPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.YELLOW);
        panel.setPreferredSize(new Dimension(100, 400));

        JLabel label = new JLabel("Left");
        label.setFont(panel.getFont().deriveFont(24f));
        panel.add(label);

        return panel;
    }
    public JPanel createRightPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.RED);
        panel.setPreferredSize(new Dimension(100, 400));

        JLabel label = new JLabel("Right");
        label.setFont(panel.getFont().deriveFont(24f));
        panel.add(label);


        return panel;
    }
    public JPanel createCenterPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.GREEN);
        panel.setPreferredSize(new Dimension(400, 400));

        JLabel label = new JLabel("Fixed");
        label.setFont(panel.getFont().deriveFont(24f));
        panel.add(label);

        return panel;
    }
    public void updateLeftPanel(int width, int height) {
        this.left.setPreferredSize(new Dimension(width, height));
    }

    public void updateCenterPanel(int width, int height) {
        this.center.setPreferredSize(new Dimension(width, height));
    }

    public void updateRightPanel(int width, int height) {
        this.right.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Add a panel to the `CardLayout` and to the panels `Hashmap`.
     * @param panelName
     * @param panel
     */
    public void addPanel(String panelName, DefaultPanel panel) {
        this.panels.put(panelName, panel);
        this.center.add(panel, panelName);
    }
    /**
     * This function change from one panel to another using the name of the given panel & reset the destination panel before showing it.
     * @param panelName specify the panel you want to go to using a name.
     */
    public void changePanel(String panelName) throws UnknownPanel {
        this.panels.get(panelName).resetPanel();
        this.cardLayout.show(this.center, panelName);
        //this.center.repaint();
    }

    private class PanelListener extends ComponentAdapter {
        private PanelManager frame;
        public PanelListener(PanelManager frame) {
            this.frame = frame;
        }
        @Override
        public void componentResized(ComponentEvent event) {
            JPanel panel = (JPanel) event.getSource();
            Dimension d = panel.getSize();
            if (d.width < 750 && d.height < 500) {
                d.width = 750;
                d.height = 500;
            }
            int width = (d.width - d.height) / 2;
            int height = d.height;

            this.frame.updateLeftPanel(width, height);
            this.frame.updateCenterPanel(height, height);
            this.frame.updateRightPanel(width, height);
            this.frame.mainWindow.pack();
        }
    }
}