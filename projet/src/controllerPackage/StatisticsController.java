package controllerPackage;

import businessPackage.PlayerMatchStatistics;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.statistic.MovementData;

import java.util.ArrayList;

public class StatisticsController {
    private PlayerMatchStatistics playerMatchStatistics;
    public StatisticsController(int id) {
        this.playerMatchStatistics = new PlayerMatchStatistics(id);
    }
    public StatisticsController(IdAccount id) {
        this.playerMatchStatistics = new PlayerMatchStatistics(id);
    }
    public void setStatistic() throws ResearchDataAccessException {
        playerMatchStatistics.setStatistic();
    }

    public ArrayList<MovementData> getOpeningList() {
        return playerMatchStatistics.getOpeningList();
    }

    public ArrayList<MovementData> getDefenseList() {
        return playerMatchStatistics.getDefenseList();
    }

    public ArrayList<MovementData> getAttackList() {
        return playerMatchStatistics.getAttackList();
    }


    @Override
    public String toString() {
        return playerMatchStatistics.toString();
    }

}
