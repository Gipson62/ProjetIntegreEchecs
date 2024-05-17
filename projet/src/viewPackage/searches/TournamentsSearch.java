package viewPackage.searches;

import controllerPackage.ResearchController;
import viewPackage.DefaultPanel;
import viewPackage.MainWindow;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TournamentsSearch extends DefaultPanel {
    PanelManager panelManager;
    JPanel formPanel, resultPanel, buttonsPanel;
    JTable results;
    ResearchController researchController;
    ValidateButton validateButton;
    public TournamentsSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setSize(150, 150);
        GridLayout gridLayout = new GridLayout(3, 2, 5, 5);
        this.formPanel.setLayout(gridLayout);

        this.formPanel.add(new JLabel("utilisateur"));
        String[] usersList = {"Gipson62#8015", "Salut#208", "....#sgh"};
        JComboBox users = new JComboBox(usersList);
        this.formPanel.add(users);

        this.formPanel.add(new JLabel("date"));
        JSpinner dateSpinner = new JSpinner();
        this.formPanel.add(dateSpinner);

        this.formPanel.add(new JLabel("état"));
        String[] values = {"finale", "demi-finale", "quart", "huitième"};
        JComboBox states = new JComboBox(values);
        states.setSelectedItem("huitième");
        this.formPanel.add(states);

        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(1, 2, 5, 5);
        this.buttonsPanel.setLayout(gridLayout1);
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
        return;
    }
    private class ValidateButton extends JButton {
        public ValidateButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
        }
    }
}
