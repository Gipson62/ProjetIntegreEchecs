package modelPackage.tables;

import modelPackage.research.ResultFiltredMatch;
import modelPackage.research.ResultTournamentPlayed;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class TournamentSearchModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ResultTournamentPlayed> contents;
    public TournamentSearchModel(ArrayList<ResultTournamentPlayed> allTournamentPlayed) {
        this.columnNames = new ArrayList<>();
        this.columnNames.add("Nom");
        this.columnNames.add("Date");
        this.columnNames.add("Elo");
        this.columnNames.add("Durée du match");
        this.columnNames.add("Vainqueur du tournoi");
        this.contents = allTournamentPlayed;
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
    public Class getColumnClass(int column) {
        return switch(column) {
            case 1 -> LocalDate.class;
            case 2, 3 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultTournamentPlayed resultTournamentPlayed = this.contents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> resultTournamentPlayed.getNameTournament();
            case 1 -> resultTournamentPlayed.getDateMatch();
            case 2 -> resultTournamentPlayed.getTournamentElo();
            case 3 -> resultTournamentPlayed.getTimeMatch();
            default -> resultTournamentPlayed.getUserTournamentWinner();
        };
    }
}
