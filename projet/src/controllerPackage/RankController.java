package controllerPackage;

import businessPackage.RankManager;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;

import java.util.ArrayList;

public class RankController {
    RankManager rankManager;
    public RankController() {
        this.rankManager = new RankManager();
    }
    public ArrayList<Rank> getAllRanks() throws ReadRankException {
        return rankManager.getAllRanks();
    }
    public Rank getRankById(int id) throws ReadRankException {
        return rankManager.getRankById(id);
    }
}
