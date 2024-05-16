package businessPackage;

import dataAccessPackage.rankDataAccess.RankDataAccess;
import dataAccessPackage.rankDataAccess.RankDBAccess;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;

import java.util.ArrayList;

public class RankManager {
    RankDataAccess dao;
    ArrayList<Rank> allRanks;

    public RankManager(){
        dao = new RankDBAccess();
    }

    public ArrayList<Rank> getAllRanks() throws ReadRankException {
        if (allRanks == null)
            this.allRanks = dao.getAllRanks();

        return allRanks;
    }
    public Rank getRankById(int id) throws ReadRankException {
        //to ensure allRanks isn't equal to null
        if (allRanks == null)
            this.getAllRanks();
        for(Rank rank : this.allRanks) {
            if (rank.getRank() == id) {
                return rank;
            }
        }
        return null;
    }
}
