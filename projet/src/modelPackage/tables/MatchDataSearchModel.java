package modelPackage.tables;

import modelPackage.research.MatchData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MatchDataSearchModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<MatchData> contents;
    public MatchDataSearchModel(ArrayList<MatchData> allMatches) {
        this.columnNames = new ArrayList<>();
        this.columnNames.add("Pseudo");
        this.columnNames.add("Adversaire");
        this.columnNames.add("Coups");
        this.columnNames.add("Attaques utilisées");
        this.columnNames.add("Défenses utilisées");
        this.columnNames.add("Ouvertures utilisées");
        this.columnNames.add("Résultat");
        this.contents = allMatches;
    }
    @Override
    public int getRowCount() {
        return this.contents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MatchData matchData = this.contents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> matchData.getPlayer();
            case 1 -> matchData.getOpponent();
            case 2 -> String.join("=>", matchData.getMoves());
            case 3 -> matchData.getAttack();
            case 4 -> matchData.getDefense();
            case 5 -> matchData.getOpening();
            default -> matchData.getResult() == 'b' ? "noir" : "blanc";
        };
    }
    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }
}
