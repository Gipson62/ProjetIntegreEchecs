package businessPackage;

import dataAccessPackage.rankDataAccess.RankDataAccess;
import dataAccessPackage.rankDataAccess.RankDBAccess;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;

import java.util.ArrayList;

public class RankManager {
RankDataAccess dao;

    public RankManager(){
        dao = new RankDBAccess();
    }

    public ArrayList<Rank> getAllRanks() throws ReadRankException{
        return dao.getAllRanks();
    }
}
