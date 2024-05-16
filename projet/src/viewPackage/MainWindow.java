package viewPackage;

import exceptionPackage.UnknownPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private Container container;
    private JMenuBar menuBar;
    private JMenu appMenu, profilMenu, searchMenu, statMenu;
    private JMenuItem exit, home, login, signUp, myProfil, search1, search2, search3, stat1, stat2;
    private PanelManager panelManager;
    public MainWindow(String title) {
        super(title);
        this.panelManager = new PanelManager(this);
        this.add(panelManager, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(250, 250, 800, 500);

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
                    panelManager.changePanel("HomePanel");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.profilMenu = new JMenu("Profil");
        this.menuBar.add(this.profilMenu);
        this.signUp = new JMenuItem("Créer un compte");
        this.profilMenu.add(this.signUp);
        this.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panelManager.changePanel("InscriptionPanel");
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
                    panelManager.changePanel("Profiles");
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
                    panelManager.changePanel("TournamentsSearch");
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
                    panelManager.changePanel("EloSearch");
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
                    panelManager.changePanel("FriendTournamentsSearch");
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
                    panelManager.changePanel("WinratePanel");
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
                    panelManager.changePanel("OpeningsStats");
                } catch (UnknownPanel ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.pack();

        this.setVisible(true);
    }
}
