package viewPackage.searches;

import viewPackage.DefaultPanel;
import viewPackage.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EloSearch extends DefaultPanel {
    MainWindow mainWindow;
    JPanel formPanel;
    JPanel buttonsPanel;
    public EloSearch(MainWindow initMainWindow) {
        this.mainWindow = initMainWindow;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setSize(150, 150);
        GridLayout gridLayout = new GridLayout(3, 2, 5, 5);
        this.formPanel.setLayout(gridLayout);

        this.formPanel.add(new JLabel("elo"));
        JSlider eloSlider = new JSlider(0, 3000);
        this.formPanel.add(eloSlider);

        this.formPanel.add(new JLabel("date"));
        JSpinner date1Spinner = new JSpinner();
        this.formPanel.add(date1Spinner);

        this.formPanel.add(new JLabel("date"));
        JSpinner date2Spinner = new JSpinner();
        this.formPanel.add(date2Spinner);

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
}
