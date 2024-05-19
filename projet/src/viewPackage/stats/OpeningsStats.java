package viewPackage.stats;

import viewPackage.IPanel;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningsStats extends JPanel implements IPanel {
    PanelManager panelManager;
    JPanel formPanel;
    JPanel buttonsPanel;
    public OpeningsStats(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
    }
    @Override
    public void resetPanel() {
        this.removeAll();
        this.init();
        return;
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        this.formPanel.setSize(150, 150);
        GridLayout gridLayout = new GridLayout(1, 2, 5, 5);
        this.formPanel.setLayout(gridLayout);

        this.formPanel.add(new JLabel("username & tag"));
        JTextField username = new JTextField();
        this.formPanel.add(username);

        this.add(this.formPanel, BorderLayout.CENTER);

        // TODO : Add the result just under the question with a list (Opening name -> usage (%))

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
}
