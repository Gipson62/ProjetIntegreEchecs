package viewPackage.stats;

import controllerPackage.AccountController;
import controllerPackage.StatisticsController;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import viewPackage.IPanel;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WinratePanel extends JPanel implements IPanel {
    PanelManager panelManager;
    JPanel formPanel, buttonsPanel, resultPanel;
    JComboBox<String> users;
    ArrayList<Account> allAccounts;
    StatisticsController statisticsController;
    AccountController accountController;
    JTable result;
    public WinratePanel(PanelManager initPanelManager) {
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
        this.add(this.formPanel, BorderLayout.NORTH);
        this.add(this.resultPanel);

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
                        double winrate = statisticsController.getGlobalWinrate();
                        JLabel winrateLabel = new JLabel();
                        winrateLabel.setText("Taux de victoire : " + (Double.isNaN(winrate) ? "Pas de données" : String.format("%.2f", winrate)));
                        winrateLabel.setFont(getFont().deriveFont(28f));
                        resultPanel.removeAll();
                        resultPanel.add(winrateLabel);
                        resultPanel.validate();
                        resultPanel.repaint();
                    } catch (ResearchDataAccessException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
}
