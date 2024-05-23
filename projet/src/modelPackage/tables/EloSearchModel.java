package modelPackage.tables;

import modelPackage.research.ResultFiltredMatch;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class EloSearchModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ResultFiltredMatch> contents;
    public EloSearchModel(ArrayList<ResultFiltredMatch> allFilteredMatches) {
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
        ResultFiltredMatch resultFiltredMatch = this.contents.get(rowIndex);
        char winner = Objects.equals(resultFiltredMatch.getMatchWin(), "b") ? 'b' : 'w';
        return switch(columnIndex) {
            case 0 -> resultFiltredMatch.getDateMatch();
            case 1 -> winner == 'b' ? resultFiltredMatch.getUsernameBlack() : resultFiltredMatch.getUsernameWhite();
            case 2 -> winner == 'b' ? resultFiltredMatch.getEloBlack() : resultFiltredMatch.getEloWhite();
            case 3 -> winner == 'b' ? resultFiltredMatch.getUsernameWhite() : resultFiltredMatch.getUsernameBlack();
            default -> winner == 'b' ? resultFiltredMatch.getEloWhite() : resultFiltredMatch.getEloBlack();
        };
    }
}
