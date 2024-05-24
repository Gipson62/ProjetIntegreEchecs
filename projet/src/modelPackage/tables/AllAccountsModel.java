package modelPackage.tables;

import controllerPackage.RankController;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class AllAccountsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Account> contents;
    private RankController rankController;
    public AllAccountsModel(ArrayList<Account> allAccounts) {
        this.rankController = new RankController();
        this.columnNames = new ArrayList<>();
        this.columnNames.add("Identifiant");
        this.columnNames.add("Email");
        this.columnNames.add("Pseudo");
        this.columnNames.add("Rang");
        this.columnNames.add("Date de naissance");
        this.columnNames.add("Est un débutant?");
        this.columnNames.add("Elo");
        this.columnNames.add("Mot de passe");
        this.columnNames.add("Bio");
        this.columnNames.add("Tag");
        this.columnNames.add("Genre");
        this.contents = allAccounts;
    }
    @Override
    public int getRowCount() {
        return this.contents.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = this.contents.get(rowIndex);
        return switch(columnIndex) {
            case 0 -> account.getIdAccount();
            case 1 -> account.getEmail();
            case 2 -> account.getUsername();
            case 3 -> {
                try {
                    yield rankController.getRankById(account.getRank()).getName();
                } catch (ReadRankException e) {
                    throw new RuntimeException(e);
                }
            }
            case 4 -> account.getBirthdate();
            case 5 -> account.getIsBeginner();
            case 6 -> account.getElo();
            case 7 -> account.getPassword();
            case 8 -> account.getBio();
            case 9 -> account.getTag();
            default -> account.getGender();
        };
    }

    @Override
    public Class getColumnClass(int column) {
        return switch (column) {
            case 4 -> LocalDate.class;
            case 5 -> Boolean.class;
            case 0, 6, 9 -> Integer.class;
            default -> String.class;
        };
    }
}