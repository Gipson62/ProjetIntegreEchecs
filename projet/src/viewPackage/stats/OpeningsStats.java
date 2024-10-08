package viewPackage.stats;

import controllerPackage.AccountController;
import controllerPackage.ResearchController;
import controllerPackage.StatisticsController;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import modelPackage.statistic.MovementData;
import modelPackage.tables.OpeningsStatsModel;
import viewPackage.IPanel;
import viewPackage.PanelManager;
import viewPackage.searches.MatchDataSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class OpeningsStats extends JPanel implements IPanel {
    private PanelManager panelManager;
    private JPanel formPanel, buttonsPanel, resultPanel;
    private JComboBox<String> users;
    private ArrayList<Account> allAccounts;
    private StatisticsController statisticsController;
    private AccountController accountController;
    private JTable result;
    public OpeningsStats(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.accountController = new AccountController();
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

        try {
            JLabel usernameLabel = new JLabel("Pseudo* :");
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            gridBag.setConstraints(usernameLabel, c);
            this.formPanel.add(usernameLabel);
            c.gridwidth = GridBagConstraints.REMAINDER;
            this.allAccounts = accountController.getAllAccounts();
            String[] usersTab = new String[this.allAccounts.size()];
            for(int i = 0; i < this.allAccounts.size(); i++){
                usersTab[i] = this.allAccounts.get(i).getUsername() + "#" + this.allAccounts.get(i).getTag();
            }
            this.users = new JComboBox<>(usersTab);
            gridBag.setConstraints(this.users, c);
            this.formPanel.add(this.users);
        } catch (ReadAccountException e) {
            throw new RuntimeException(e);
        }

        this.resultPanel = new JPanel();
        this.add(this.resultPanel);
        this.add(this.formPanel, BorderLayout.NORTH);

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.add(new ValidateButton("Valider"));
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }
    private class ValidateButton extends JButton {
        public ValidateButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        IdAccount id = allAccounts.get(users.getSelectedIndex()).getIdAccountO();
                        statisticsController = new StatisticsController(id);
                        statisticsController.setStatistic();
                        String[] column = {"Type", "Nom", "Nombre de victoire", "Nombre de défaite", "Nombre de victoire contre", "Nombre de défaite contre", "Taux de victoire avec", "Taux de victoire contre"};
                        ArrayList<MovementData> attackArray = statisticsController.getAttackList();
                        ArrayList<MovementData> openingArray = statisticsController.getOpeningList();
                        ArrayList<MovementData> defenseArray = statisticsController.getDefenseList();
                        OpeningsStatsModel openingsStatsModel = new OpeningsStatsModel(attackArray, defenseArray, openingArray);
                        result = new JTable(openingsStatsModel);
                        resultPanel.removeAll();
                        resultPanel.add(new JScrollPane(result));
                        resultPanel.validate();
                        resultPanel.repaint();
                    } catch (ResearchDataAccessException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        private void createOpeningData(String[][] openingData, int i, MovementData currDefense, String type) {
            openingData[i][0] = type;
            openingData[i][1] = currDefense.getName();
            openingData[i][2] = String.valueOf(currDefense.getWinWith());
            openingData[i][3] = String.valueOf(currDefense.getLoseWith());
            openingData[i][4] = String.valueOf(currDefense.getWinAgainst());
            openingData[i][5] = String.valueOf(currDefense.getLoseAgainst());
            openingData[i][6] = currDefense.getWinRateWith() == null ? "no data" : String.valueOf(currDefense.getWinRateWith());
            openingData[i][7] = currDefense.getWinRateAgainst() == null ? "no data" : String.valueOf(currDefense.getWinRateAgainst());
        }
    }
}
