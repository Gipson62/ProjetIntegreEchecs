package userInterface;

import userInterface.profile.InscriptionPanel;
import userInterface.profile.LoginPanel;
import userInterface.searches.EloSearch;
import userInterface.searches.FriendTournamentsSearch;
import userInterface.searches.TournamentsSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private Container container;
    private JMenuBar menuBar;
    private JMenu appMenu, profilMenu, searchMenu, statMenu;
    private JMenuItem exit, home, login, signUp, myProfil, search1, search2, search3, stat1, stat2;
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

        this.container.add("HomePanel", new HomePanel(this));
        this.container.add("LoginPanel", new LoginPanel(this));
        this.container.add("InscriptionPanel", new InscriptionPanel(this));
        this.container.add("EloSearch", new EloSearch(this));
        this.container.add("TournamentsSearch", new TournamentsSearch(this));
        this.container.add("FriendTournamentsSearch", new FriendTournamentsSearch(this));
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
                changePanel("HomePanel");
            }
        });

        this.profilMenu = new JMenu("Profil");
        this.menuBar.add(this.profilMenu);
        this.login = new JMenuItem("Se connecter");
        this.profilMenu.add(this.login);
        this.login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel("LoginPanel");
            }
        });
        this.signUp = new JMenuItem("Créer un compte");
        this.profilMenu.add(this.signUp);
        this.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel("InscriptionPanel");
            }
        });
        this.myProfil = new JMenuItem("Mon profil");
        this.profilMenu.add(this.myProfil);
        this.myProfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : add a transition to the MyProfil Panel IF he's logged in
            }
        });

        this.searchMenu = new JMenu("Recherches");
        this.menuBar.add(this.searchMenu);
        this.search1 = new JMenuItem("Tournois d'un joueur");
        this.searchMenu.add(this.search1);
        this.search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel("TournamentsSearch");
            }
        });
        this.search2 = new JMenuItem("Parties entre 2 dates");
        this.searchMenu.add(this.search2);
        this.search2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel("EloSearch");
            }
        });
        this.search3 = new JMenuItem("Tournois d'un ami");
        this.searchMenu.add(this.search3);
        this.search3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel("FriendTournamentsSearch");
            }
        });

        this.statMenu = new JMenu("Statistiques");
        this.menuBar.add(this.statMenu);
        this.stat1 = new JMenuItem("Blanc/Noir winrate");
        this.statMenu.add(this.stat1);
        this.stat1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : add a transition to the First Stat Panel
            }
        });
        this.stat2 = new JMenuItem("Utilisation des ouvertures");
        this.statMenu.add(this.stat2);
        this.stat2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : add a transition to the First Search Panel
            }
        });

        this.setVisible(true);
    }
    public void changePanel(String panelName) {
        this.cardLayout.show(this.container, panelName);
    }
}
