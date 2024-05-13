package dataAccessPackage.rankDataAccess;

import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;

import java.util.ArrayList;

public interface RankDataAccess {

    ArrayList<Rank> getAllRanks() throws ReadRankException;
}
