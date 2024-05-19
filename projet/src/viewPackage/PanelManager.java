package viewPackage;

import exceptionPackage.UnknownPanel;
import viewPackage.profile.InscriptionPanel;
import viewPackage.profile.ModificationPanel;
import viewPackage.profile.Profiles;
import viewPackage.searches.EloSearch;
import viewPackage.searches.MatchDataSearch;
import viewPackage.searches.TournamentsSearch;
import viewPackage.stats.OpeningsStats;
import viewPackage.stats.WinratePanel;
import viewPackage.thread.ChessThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class PanelManager extends JPanel {
    private JPanel left;
    public RightPanel right;
    private JPanel center;
    private MainWindow mainWindow;
    private HashMap<String, JPanel> panels;
    private CardLayout cardLayout;
    private JPanel container;
    private PanelListener listener;
    private ChessThread chessThread;
    public PanelManager(MainWindow mainWindow) {
        super(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.mainWindow = mainWindow;
        listener = new PanelListener(this);
        this.addComponentListener(listener);
        this.left = createLeftPanel();
        this.right = createRightPanel();
        this.center = createCenterPanel();
        this.chessThread = new ChessThread(this);
        this.chessThread.start();

        this.add(this.left);
        this.add(this.center);
        this.add(this.right);

        this.cardLayout = new CardLayout();
        this.center.setLayout(new BorderLayout());

        this.panels = new HashMap<>();

        container = new JPanel();
        this.container.setLayout(cardLayout);

        this.addPanel("HomePanel", new HomePanel(this), container);
        this.addPanel("InscriptionPanel", new InscriptionPanel(this), container);
        this.addPanel("EloSearch", new EloSearch(this), container);
        this.addPanel("TournamentsSearch", new TournamentsSearch(this), container);
        this.addPanel("MatchDataSearch", new MatchDataSearch(this), container);
        this.addPanel("Profiles", new Profiles(this), container);
        this.addPanel("OpeningsStats", new OpeningsStats(this), container);
        this.addPanel("WinratePanel", new WinratePanel(this), container);
        this.addPanel("ModificationPanel", new ModificationPanel(this), container);
        this.center.add(container, BorderLayout.CENTER);
        System.out.println(this);
    }

    public HashMap<String, JPanel> getPanels() {
        return panels;
    }
    public JPanel createLeftPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(100, 400));

        return panel;
    }
    public RightPanel createRightPanel() {
        RightPanel panel = new RightPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(100, 400));

        return panel;
    }
    public JPanel createCenterPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(400, 500));

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
    public void addPanel(String panelName, JPanel panel, JPanel parent) {
        this.panels.put(panelName, panel);
        parent.add(panel, panelName);
    }
    /**
     * This function change from one panel to another using the name of the given panel & reset the destination panel before showing it.
     * @param panelName specify the panel you want to go to using a name.
     */
    public void changePanel(String panelName) throws UnknownPanel {
        this.cardLayout.show(container, panelName);
        ((IPanel)this.panels.get(panelName)).enterPanel();
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
            if (d.width < 800) {
                d.width = 800;
            }
            if (d.height < 500) {
                d.height = 500;
            }
            int width = (d.width) / 8;
            int height = d.height;

            this.frame.updateLeftPanel(width, height);
            this.frame.updateCenterPanel(width*6, height);
            this.frame.updateRightPanel(width, height);
            this.frame.mainWindow.pack();
        }
    }
    public class RightPanel extends JPanel {
        public BufferedImage currImage;
        public RightPanel(FlowLayout flowLayout) {
            super(flowLayout);
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int width = this.getWidth();
            Image i = this.currImage.getScaledInstance(width, width, Image.SCALE_DEFAULT);
            g.drawImage(i, 0, this.getHeight()/2 - width/2, this);
        }

        public void setCurrImage(BufferedImage currImage) {
            this.currImage = currImage;
        }
    }
}
