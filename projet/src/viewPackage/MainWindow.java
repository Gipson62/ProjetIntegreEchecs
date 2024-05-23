package viewPackage;

import dataAccessPackage.SingletonConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu appMenu, profilMenu, searchMenu, statMenu;
    private JMenuItem exit, home, inscription, profiles, playerTournaments, gameBetweenTwoDates, playerAllMatches, winrate, OpeningUsage;
    private PanelManager panelManager;
    public MainWindow(String title) {
        super(title);
        this.panelManager = new PanelManager(this);
        this.add(panelManager, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SingletonConnection.closeConnection();
                    System.exit(0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error while trying to close the connexion to the database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
                panelManager.changePanel("HomePanel");
            }
        });
        this.profilMenu = new JMenu("Profil");
        this.menuBar.add(this.profilMenu);
        this.inscription = new JMenuItem("Créer un compte");
        this.profilMenu.add(this.inscription);
        this.inscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("InscriptionPanel");
            }
        });
        this.profiles = new JMenuItem("Profiles");
        this.profilMenu.add(this.profiles);

        this.profiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("Profiles");
            }
        });

        this.searchMenu = new JMenu("Recherches");
        this.menuBar.add(this.searchMenu);
        this.playerTournaments = new JMenuItem("Tournois d'un joueur");
        this.searchMenu.add(this.playerTournaments);
        this.playerTournaments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("TournamentsSearch");
            }
        });
        this.gameBetweenTwoDates = new JMenuItem("Parties entre 2 dates");
        this.searchMenu.add(this.gameBetweenTwoDates);
        this.gameBetweenTwoDates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("EloSearch");
            }
        });
        this.playerAllMatches = new JMenuItem("Matchs d'un joueur");
        this.searchMenu.add(this.playerAllMatches);
        this.playerAllMatches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("MatchDataSearch");
            }
        });

        this.statMenu = new JMenu("Statistiques");
        this.menuBar.add(this.statMenu);
        this.winrate = new JMenuItem("Blanc/Noir winrate");
        this.statMenu.add(this.winrate);
        this.winrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("WinratePanel");
            }
        });
        this.OpeningUsage = new JMenuItem("Utilisation des ouvertures");
        this.statMenu.add(this.OpeningUsage);
        this.OpeningUsage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel("OpeningsStats");
            }
        });

        this.pack();

        this.setVisible(true);
    }
}
