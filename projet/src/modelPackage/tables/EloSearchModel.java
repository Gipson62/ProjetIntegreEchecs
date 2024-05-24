package modelPackage.tables;

import modelPackage.research.ResultFilteredMatch;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EloSearchModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ResultFilteredMatch> contents;
    public EloSearchModel(ArrayList<ResultFilteredMatch> allFilteredMatches) {
        this.columnNames = new ArrayList<>();
        this.columnNames.add("Date du début");
        this.columnNames.add("Pseudo (vainqueur)");
        this.columnNames.add("Elo (vainqueur)");
        this.columnNames.add("Pseudo (perdant)");
        this.columnNames.add("Elo (perdant)");
        this.contents = allFilteredMatches;
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
            case 0 -> LocalDate.class;
            case 2, 4 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ResultFilteredMatch resultFilteredMatch = this.contents.get(rowIndex);
        char winner = Objects.equals(resultFilteredMatch.getMatchWin(), "b") ? 'b' : 'w';
        return switch(columnIndex) {
            case 0 -> resultFilteredMatch.getDateMatch();
            case 1 -> winner == 'b' ? resultFilteredMatch.getUsernameBlack() : resultFilteredMatch.getUsernameWhite();
            case 2 -> winner == 'b' ? resultFilteredMatch.getEloBlack() : resultFilteredMatch.getEloWhite();
            case 3 -> winner == 'b' ? resultFilteredMatch.getUsernameWhite() : resultFilteredMatch.getUsernameBlack();
            default -> winner == 'b' ? resultFilteredMatch.getEloWhite() : resultFilteredMatch.getEloBlack();
        };
    }
}
