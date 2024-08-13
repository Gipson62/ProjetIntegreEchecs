package viewPackage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EloSlider extends JSlider {
    private JLabel eloLabel;
    static final int ELO_MIN = 0;
    static final int ELO_MAX = 3000;
    public EloSlider(JLabel initEloLabel) {
        super(ELO_MIN, ELO_MAX);
        this.eloLabel = initEloLabel;
        this.setMajorTickSpacing(500);
        this.setMinorTickSpacing(250);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        eloLabel.setText("Elo* : (" + this.getValue() + ")");
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if(!source.getValueIsAdjusting()) {
                    int elo = (int)source.getValue();
                    eloLabel.setText("Elo* : ("+elo+")");
                }
            }
        });
    }
}
