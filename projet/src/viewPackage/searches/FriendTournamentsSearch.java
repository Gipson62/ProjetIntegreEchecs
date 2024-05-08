package viewPackage.searches;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendTournamentsSearch extends DefaultPanel {
    PanelManager panelManager;
    JPanel formPanel, buttonsPanel, titlePanel, resultPanel;
    JComboBox users;
    public FriendTournamentsSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.titlePanel = new JPanel();
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Recherche 3");
        title.setFont(this.getFont().deriveFont(28f));
        this.titlePanel.add(title, BorderLayout.CENTER);
        this.add(this.titlePanel, BorderLayout.NORTH);

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        JLabel friendsLabel = new JLabel("Amis :");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        gridBag.setConstraints(friendsLabel, c);
        this.formPanel.add(friendsLabel);
        // TODO : getAllUsers()
        c.gridwidth = GridBagConstraints.REMAINDER;
        String[] usersList = {"Gipson62#8015", "Salut#208", "....#sgh"};
        this.users = new JComboBox(usersList);
        gridBag.setConstraints(this.users, c);
        this.formPanel.add(this.users);

        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.add(new JButton("Valider"));
        JButton inscriptionButton = new JButton("Recommencer");
        this.buttonsPanel.add(inscriptionButton, BorderLayout.SOUTH);
        inscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPanel();
            }
        });
        this.add(buttonsPanel, BorderLayout.SOUTH);

    }
    @Override
    public void resetPanel() {
        this.users.setSelectedIndex(0);
    }
}
