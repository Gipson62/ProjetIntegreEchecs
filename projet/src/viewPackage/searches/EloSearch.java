package viewPackage.searches;

import controllerPackage.RankController;
import controllerPackage.ResearchController;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.research.FilterMatch;
import modelPackage.research.ResultFilteredMatch;
import modelPackage.tables.EloSearchModel;
import viewPackage.IPanel;
import viewPackage.EloSlider;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class EloSearch extends JPanel implements IPanel {
    PanelManager panelManager;
    JPanel formPanel, buttonsPanel, resultPanel;
    JSlider eloSlider;
    JSpinner date1, date2;
    RankController rankController;
    ResearchController researchController;
    JTable result;
    public EloSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.rankController = new RankController();
        this.researchController = new ResearchController();
    }
    @Override
    public void enterPanel() {
        this.removeAll();
        this.init();
        return;
    }
    @Override
    public void init() {
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

        this.add(this.formPanel, BorderLayout.NORTH);

        this.resultPanel = new JPanel();
        this.add(this.resultPanel);
        this.result = new JTable();
        this.resultPanel.add(new JScrollPane(this.result));

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.add(new SearchButton("Rechercher"));
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private class SearchButton extends JButton{
        public SearchButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDate dateMin = ((java.util.Date) date1.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate dateMax = ((java.util.Date) date2.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if(dateMin.isBefore(dateMax)) {
                        FilterMatch filterMatch = new FilterMatch(eloSlider.getValue(), dateMin, dateMax);
                        try {
                            ArrayList<ResultFilteredMatch> res = researchController.getFilteredMatch(filterMatch);
                            EloSearchModel eloSearchModel = new EloSearchModel(res);
                            resultPanel.removeAll();
                            result = new JTable(eloSearchModel);
                            resultPanel.add(new JScrollPane(result));
                            resultPanel.validate();
                            resultPanel.repaint();
                        } catch (ResearchDataAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La première date doit être plus petite que la deuxième", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
