package viewPackage.searches;

import controllerPackage.AccountController;
import controllerPackage.ResearchController;
import controllerPackage.TournamentStateController;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.MatchData;
import modelPackage.tables.MatchDataSearchModel;
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
    public void enterPanel() {
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
            JLabel usernameLabel = new JLabel("Pseudo :");
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
        this.validateButton = new ValidateButton("Valider");
        this.buttonsPanel.add(this.validateButton);
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
                        MatchDataSearchModel matchDataSearchModel = new MatchDataSearchModel(matches);
                        resultPanel.removeAll();
                        result = new JTable(matchDataSearchModel);
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
