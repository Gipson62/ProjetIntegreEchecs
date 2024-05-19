package viewPackage.searches;

import controllerPackage.AccountController;
import controllerPackage.ResearchController;
import controllerPackage.TournamentStateController;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.MatchData;
import viewPackage.IPanel;
import viewPackage.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MatchDataSearch extends JPanel implements IPanel {
    PanelManager panelManager;
    JPanel formPanel, resultPanel, buttonsPanel;
    JTable results;
    TournamentStateController tournamentStateController;
    JComboBox<String> users;
    ArrayList<Account> allAccounts;
    ResearchController researchController;
    AccountController accountController;
    JTable result;
    ValidateButton validateButton;
    public MatchDataSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.researchController = new ResearchController();
        this.accountController = new AccountController();
        this.tournamentStateController = new TournamentStateController();
    }
    @Override
    public void resetPanel() {
        this.removeAll();
        this.init();
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());

        this.formPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.formPanel.setLayout(gridBag);

        try {
            JLabel usernameLabel = new JLabel("Username :");
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

    private class ValidateButton extends JButton{
        public ValidateButton(String text) {
            super(text);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        IdAccount id = allAccounts.get(users.getSelectedIndex()).getIdAccountO();
                        ArrayList<MatchData> matches = researchController.getMatchData(id);
                        resultPanel.removeAll();
                        String[] columnNames = {"Pseudo", "Adversaire", "Coups", "Attaque utilisé", "Defense utilisée", "Ouverture utilsiée", "Résultat"};
                        String[][] data = new String[matches.size()][7];
                        for(int i = 0; i < matches.size(); i++){
                            MatchData currMatch = matches.get(i);
                            data[i][0] = currMatch.getPlayer();
                            data[i][1] = currMatch.getOpponent();
                            data[i][2] = currMatch.getMoves();
                            data[i][3] = currMatch.getAttack().isEmpty() ? "Attaque inconnue" : currMatch.getAttack();
                            data[i][4] = currMatch.getDefense().isEmpty() ? "Defense inconnue" : currMatch.getDefense();
                            data[i][5] = currMatch.getOpening().isEmpty() ? "Defense inconnue" : currMatch.getOpening();
                            data[i][6] = currMatch.getWinOrLose();
                        }
                        result = new JTable(data, columnNames);
                        resultPanel.add(new JScrollPane(result));
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
