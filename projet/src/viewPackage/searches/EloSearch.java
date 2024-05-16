package viewPackage.searches;

import viewPackage.DefaultPanel;
import viewPackage.EloSlider;
import viewPackage.PanelManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class EloSearch extends DefaultPanel {
    PanelManager panelManager;
    JPanel formPanel;
    JSlider eloSlider;
    JSpinner date1, date2;
    JPanel buttonsPanel;
    public EloSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        JLabel eloLabel = new JLabel("Elo :");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        gridBag.setConstraints(eloLabel, c);
        this.formPanel.add(eloLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.eloSlider = new EloSlider(eloLabel);
        gridBag.setConstraints(this.eloSlider, c);
        this.formPanel.add(this.eloSlider);

        JLabel dateLabel = new JLabel("Première date :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(dateLabel, c);
        this.formPanel.add(dateLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        this.date1 = new JSpinner(new SpinnerDateModel());
        date1.setEditor(new JSpinner.DateEditor(date1, model.toPattern()));
        gridBag.setConstraints(this.date1, c);
        this.formPanel.add(this.date1);

        JLabel date2Label = new JLabel("Deuxième date :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(date2Label, c);
        this.formPanel.add(date2Label);
        c.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model2 = new SimpleDateFormat("dd/MM/yyyy");
        this.date2 = new JSpinner(new SpinnerDateModel());
        date2.setEditor(new JSpinner.DateEditor(date2, model2.toPattern()));
        gridBag.setConstraints(this.date2, c);
        this.formPanel.add(this.date2);

        this.add(this.formPanel, BorderLayout.CENTER);

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.add(new JButton("Valider"));
        JButton inscriptionButton = new JButton("Recommencer");
        this.buttonsPanel.add(inscriptionButton);
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
