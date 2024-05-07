package viewPackage;

import exceptionPackage.UnknownPanel;
import viewPackage.profile.InscriptionPanel;
import viewPackage.profile.LoginPanel;
import viewPackage.profile.MyProfile;
import viewPackage.searches.EloSearch;
import viewPackage.searches.FriendTournamentsSearch;
import viewPackage.searches.TournamentsSearch;
import viewPackage.stats.OpeningsStats;
import viewPackage.stats.Winrate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private Container container;
    private JMenuBar menuBar;
    private JMenu appMenu, profilMenu, searchMenu, statMenu;
    private JMenuItem exit, home, login, signUp, myProfil, search1, search2, search3, stat1, stat2;
    private HashMap<String, DefaultPanel> panels;
    public MainWindow() {
        super("Application d'échecs");
        this.setBounds(250, 250, 750, 750);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.cardLayout = new CardLayout();
        this.container = this.getContentPane();
        this.container.setLayout(this.cardLayout);

        this.panels = new HashMap<>();

        this.addPanel("HomePanel", new HomePanel(this));
        this.addPanel("LoginPanel", new LoginPanel(this));
        this.addPanel("InscriptionPanel", new InscriptionPanel(this));
        this.addPanel("EloSearch", new EloSearch(this));
        this.addPanel("TournamentsSearch", new TournamentsSearch(this));
        this.addPanel("FriendTournamentsSearch", new FriendTournamentsSearch(this));
        this.addPanel("MyProfile", new MyProfile(this));
        this.addPanel("OpeningsStats", new OpeningsStats(this));
        this.addPanel("Winrate", new Winrate(this));
        this.cardLayout.show(this.container, "HomePanel");


        this.menuBar = new JMenuBar();
        this.setJMenuBar(this.menuBar);

        this.appMenu = new JMenu("Application");
        this.menuBar.add(this.appMenu);
        this.exit = new JMenuItem("Quitter");
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.appMenu.add(this.exit);
        this.home = new JMenuItem("Accueil");
        this.appMenu.add(this.home);
        this.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("HomePanel");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.profilMenu = new JMenu("Profil");
        this.menuBar.add(this.profilMenu);
        this.login = new JMenuItem("Se connecter");
        this.profilMenu.add(this.login);
        this.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("LoginPanel");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.signUp = new JMenuItem("Créer un compte");
        this.profilMenu.add(this.signUp);
        this.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("InscriptionPanel");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.myProfil = new JMenuItem("Mon profil");
        this.profilMenu.add(this.myProfil);
        this.myProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("MyProfile");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.searchMenu = new JMenu("Recherches");
        this.menuBar.add(this.searchMenu);
        this.search1 = new JMenuItem("Tournois d'un joueur");
        this.searchMenu.add(this.search1);
        this.search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("TournamentsSearch");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.search2 = new JMenuItem("Parties entre 2 dates");
        this.searchMenu.add(this.search2);
        this.search2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("EloSearch");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.search3 = new JMenuItem("Tournois d'un ami");
        this.searchMenu.add(this.search3);
        this.search3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("FriendTournamentsSearch");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.statMenu = new JMenu("Statistiques");
        this.menuBar.add(this.statMenu);
        this.stat1 = new JMenuItem("Blanc/Noir winrate");
        this.statMenu.add(this.stat1);
        this.stat1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("Winrate");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.stat2 = new JMenuItem("Utilisation des ouvertures");
        this.statMenu.add(this.stat2);
        this.stat2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changePanel("OpeningsStats");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.setVisible(true);
    }

    /**
     * This function change from one panel to another using the name of the given panel & reset the destination panel before showing it.
     * @param panelName specify the panel you want to go to using a name.
     */
    public void changePanel(String panelName) throws UnknownPanel {
        this.panels.get(panelName).resetPanel();
        this.cardLayout.show(this.container, panelName);
    }

    /**
     * Add a panel to the `CardLayout` and to the panels `Hashmap`.
     * @param panelName
     * @param panel
     */
    public void addPanel(String panelName, DefaultPanel panel) {
        this.panels.put(panelName, panel);
        this.container.add(panelName, panel);
    }
}
