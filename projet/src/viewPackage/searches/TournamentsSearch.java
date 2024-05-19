package viewPackage.searches;

import controllerPackage.AccountController;
import controllerPackage.ResearchController;
import controllerPackage.TournamentStateController;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.ResearchDataAccessException;
import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.FilterTournamentPlayed;
import modelPackage.research.ResultTournamentPlayed;
import modelPackage.tournamentState.State;
import viewPackage.IPanel;
import viewPackage.PanelManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;

public class TournamentsSearch extends JPanel implements IPanel {
    PanelManager panelManager;
    JPanel formPanel, resultPanel, buttonsPanel;
    JTextField username;
    JSpinner date;
    JTable results;
    TournamentStateController tournamentStateController;
    JComboBox<String> states, users;
    ArrayList<Account> allAccounts;
    ResearchController researchController;
    AccountController accountController;
    JTable result;
    ValidateButton validateButton;
    public TournamentsSearch(PanelManager initPanelManager) {
        this.panelManager = initPanelManager;
        this.tournamentStateController = new TournamentStateController();
        this.accountController = new AccountController();
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
        this.formPanel.setSize(150, 150);

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

        JLabel dateLabel = new JLabel("Date :");
        c.gridwidth = GridBagConstraints.RELATIVE;
        gridBag.setConstraints(dateLabel, c);
        this.formPanel.add(dateLabel);
        c.gridwidth = GridBagConstraints.REMAINDER;
        SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
        this.date = new JSpinner(new SpinnerDateModel());
        date.setEditor(new JSpinner.DateEditor(date, model.toPattern()));
        gridBag.setConstraints(this.date, c);
        this.formPanel.add(this.date);

        try {
            JLabel stateLabel = new JLabel("État :");
            c.gridwidth = GridBagConstraints.RELATIVE;
            gridBag.setConstraints(stateLabel, c);
            this.formPanel.add(stateLabel);
            c.gridwidth = GridBagConstraints.REMAINDER;
            ArrayList<State> stateArrayList = tournamentStateController.getAllStates();
            String[] statesTab = new String[stateArrayList.size()];
            for(int i = 0; i < stateArrayList.size(); i++) {
                statesTab[i] = stateArrayList.get(i).getState();
            }
            states = new JComboBox<String>(statesTab);
            gridBag.setConstraints(states, c);
            this.formPanel.add(states);
        } catch (ReadTournamentStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.add(this.formPanel, BorderLayout.NORTH);

        this.resultPanel = new JPanel();
        this.add(this.resultPanel);
        this.resultPanel.add(new JScrollPane(new JTable()));

        this.buttonsPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(1, 2, 5, 5);
        this.buttonsPanel.setLayout(gridLayout1);
        this.validateButton = new ValidateButton("Valider");
        this.buttonsPanel.add(this.validateButton);
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
                        FilterTournamentPlayed filterTournamentPlayed = new FilterTournamentPlayed(id, (String) states.getSelectedItem(), ((java.util.Date) date.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        ArrayList<ResultTournamentPlayed> tournaments = researchController.getTournamentPlayed(filterTournamentPlayed);
                        resultPanel.removeAll();
                        String[] columnNames = {"Nom", "Date", "Elo", "Moment", "Vainqueur"};
                        String[][] data = new String[tournaments.size()][5];
                        for(int i = 0; i < tournaments.size(); i++){
                            ResultTournamentPlayed currTournament = tournaments.get(i);
                            data[i][0] = currTournament.getNameTournament();
                            data[i][1] = currTournament.getDateMatch().toString();
                            data[i][2] = String.valueOf(currTournament.getTournamentElo());
                            data[i][3] = String.valueOf(currTournament.getTimeMatch());
                            data[i][4] = currTournament.getUserTournamentWinner();
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
