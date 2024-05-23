package modelPackage.tables;

import modelPackage.research.MatchData;
import modelPackage.statistic.MovementData;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OpeningsStatsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<MovementData> contents;
    int attackSize;
    int defenseSize;
    int openingSize;
    public OpeningsStatsModel(ArrayList<MovementData> attackList, ArrayList<MovementData> defenseList, ArrayList<MovementData> openingList) {
        this.columnNames = new ArrayList<>();
        this.columnNames.add("Type");
        this.columnNames.add("Nom");
        this.columnNames.add("Nombre de victoire");
        this.columnNames.add("Nombre de défaite");
        this.columnNames.add("Nombre de victoire contre");
        this.columnNames.add("Nombre de défaite contre");
        this.columnNames.add("Taux de victoire avec");
        this.columnNames.add("Taux de victoire contre");
        this.columnNames.add("Taux d'utilisation");

        this.contents = new ArrayList<>();
        this.contents.addAll(attackList);
        this.attackSize = attackList.size();
        this.contents.addAll(defenseList);
        this.defenseSize = defenseList.size();
        this.contents.addAll(openingList);
        this.openingSize = openingList.size();
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
        MovementData movementData = this.contents.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> {
                if (columnIndex < this.attackSize) {
                    yield "Attaque";
                } else if(columnIndex < this.attackSize+this.defenseSize) {
                    yield "Défense";
                } else {
                    yield "Ouverture";
                }
            }
            case 1 -> movementData.getName();
            case 2 -> movementData.getWinWith();
            case 3 -> movementData.getLoseWith();
            case 4 -> movementData.getWinAgainst();
            case 5 -> movementData.getLoseAgainst();
            case 6 -> movementData.getWinRateWith();
            case 8 -> movementData.getPlayRate();
            default -> movementData.getWinRateAgainst();
        };
    }
    @Override
    public Class getColumnClass(int column) {
        return switch(column) {
            case 0, 1 -> String.class;
            case 6, 7, 8 -> Double.class;
            default -> Integer.class;
        };
    }
}
