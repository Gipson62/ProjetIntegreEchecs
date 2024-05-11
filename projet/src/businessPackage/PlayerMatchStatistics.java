package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.MatchData;
import modelPackage.statistic.MovementData;

import java.util.ArrayList;

public class PlayerMatchStatistics {
    //possede une liste d'Opening une liste de Defense et une liste d'attaque.
    //dedans le nom, les id, String composé pour faire liste de coups

    //puis les données seront stat seront calculé en fonction de ces listes
    private ArrayList<MovementData> openingList;
    private ArrayList<MovementData> defenseList;
    private ArrayList<MovementData> attackList;
    private IdAccount accountForResearch;

    public PlayerMatchStatistics(IdAccount idAccount) {
        this.openingList = new ArrayList<>();
        this.defenseList = new ArrayList<>();
        this.attackList = new ArrayList<>();
        this.accountForResearch = idAccount;
    }
    public PlayerMatchStatistics(int idAccount) {
        this(new IdAccount(idAccount));
    }

    public void setStatistic() throws ResearchDataAccessException {
        ResearchManager researchManager = new ResearchManager();
        for (MatchData matchData : researchManager.getMatchData(accountForResearch)) {


            
        }
    }


    public ArrayList<MovementData> getOpeningList() {
        return openingList;
    }

    public ArrayList<MovementData> getDefenseList() {
        return defenseList;
    }

    public ArrayList<MovementData> getAttackList() {
        return attackList;
    }


    @Override
    public String toString() {
        return "PlayerMatchStatistics{" +
                "openingList=" + openingList +
                ", defenseList=" + defenseList +
                ", attackList=" + attackList +
                '}';
    }


}
