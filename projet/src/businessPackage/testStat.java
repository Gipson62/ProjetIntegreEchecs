package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.MatchData;
import modelPackage.statistic.MovementData;

import java.util.ArrayList;
public class testStat {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("testStat");
        PlayerMatchStatistics playerMatchStatistics = new PlayerMatchStatistics(1);
        try {
            System.out.println("setStatistic");
            playerMatchStatistics.setStatistic();
            System.out.println("getAttackList");
            for (MovementData attack : playerMatchStatistics.getAttackList()) {
                System.out.println(attack);
            }
            System.out.println("getDefenseList");
            for (MovementData defense : playerMatchStatistics.getDefenseList()) {
                System.out.println(defense);
            }
            System.out.println("getOpeningList");
            for (MovementData opening : playerMatchStatistics.getOpeningList()) {
                System.out.println(opening);
            }


        } catch (ResearchDataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
