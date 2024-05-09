package viewPackage.profile;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class MyProfile extends DefaultPanel {
    PanelManager panelManager;
    JPanel infoPanel, friendsPanel, buttonsPanel;
    JTextField email, pseudo, tag, id;
    JPasswordField password;
    JSlider elo;
    JSpinner birthdate;
    JCheckBox beginner;
    JTable friends;
    public MyProfile(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.infoPanel = new JPanel();
        this.setLayout(new BorderLayout());
        GridBagLayout infoGridBag = new GridBagLayout();
        GridBagConstraints infoC = new GridBagConstraints();
        this.infoPanel.setLayout(infoGridBag);

        JLabel pseudoLabel = new JLabel("Pseudo :");
        infoC.fill = GridBagConstraints.BOTH;
        infoC.weightx = 1.0;
        infoGridBag.setConstraints(pseudoLabel, infoC);
        this.infoPanel.add(pseudoLabel);
        this.pseudo = new JTextField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.pseudo, infoC);
        this.infoPanel.add(this.pseudo);

        JLabel eloLabel = new JLabel("Elo :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(eloLabel, infoC);
        this.infoPanel.add(eloLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        this.elo = new JSlider(0, 3000);
        infoGridBag.setConstraints(this.elo, infoC);
        this.infoPanel.add(this.elo);

        JLabel emailLabel = new JLabel("Email :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(emailLabel, infoC);
        this.infoPanel.add(emailLabel);
        this.email = new JTextField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.email, infoC);
        this.infoPanel.add(this.email);

        JLabel beginnerLabel = new JLabel("Débutant :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(beginnerLabel, infoC);
        this.infoPanel.add(beginnerLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        this.beginner = new JCheckBox();
        infoGridBag.setConstraints(this.beginner, infoC);
        this.infoPanel.add(this.beginner);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(passwordLabel, infoC);
        this.infoPanel.add(passwordLabel);
        this.password = new JPasswordField();
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        infoGridBag.setConstraints(this.password, infoC);
        this.infoPanel.add(this.password);

        JLabel dateLabel = new JLabel("Date de naissance :");
        infoC.gridwidth = GridBagConstraints.RELATIVE;
        infoGridBag.setConstraints(dateLabel, infoC);
        this.infoPanel.add(dateLabel);
        infoC.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        this.birthdate = new JSpinner(new SpinnerDateModel());
        birthdate.setEditor(new JSpinner.DateEditor(birthdate, model.toPattern()));
        infoGridBag.setConstraints(this.birthdate, infoC);
        this.infoPanel.add(this.birthdate);

        this.friendsPanel = new JPanel();
        Object[][] data = new Object[4][4];
        String[] columnNames = { "Pseudo#Tag", "Email", "Elo", "Ajouter?" };
        for (int i = 0; i < 4; i++) {
            data[i][0] = "Name#" + i;
            data[i][1] = "Email@email.salut";
            data[i][2] = "elo:" + i;
            data[i][3] = new JButton("Ajouter");
        }
        this.friends = new JTable(data, columnNames);
        this.friendsPanel.setLayout(new BorderLayout());
        this.friendsPanel.add(this.friends, BorderLayout.CENTER);
        JButton addButton = new JButton("Ajouter");
        this.friendsPanel.add(addButton, BorderLayout.SOUTH);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(friends.getSelectionModel().isSelectionEmpty()) {
                    System.out.println("Nothing is selected");
                } else {
                    System.out.println(Arrays.toString(friends.getSelectionModel().getSelectedIndices()));
                }
            }
        });

        this.buttonsPanel = new JPanel();
        JButton removeButton = new JButton("Supprimer");
        this.buttonsPanel.add(removeButton);
        JButton updateButton = new JButton("Modifier");
        this.buttonsPanel.add(updateButton);

        this.add(this.infoPanel, BorderLayout.NORTH);
        this.add(this.friendsPanel, BorderLayout.CENTER);
        this.add(this.buttonsPanel, BorderLayout.SOUTH);
    }
    @Override
    public void resetPanel() {
        return;
    }
}
