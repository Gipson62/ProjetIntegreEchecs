package businessPackage;

import dataAccessPackage.rankDataAccess.RankDBAccess;
import dataAccessPackage.rankDataAccess.IRankDataAccess;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;

import java.util.ArrayList;

/**
 * Business layer class to manage operations of rank.
 */
public class RankManager {
    private IRankDataAccess dao;
    private ArrayList<Rank> allRanks;

    /**
     * Constructor initializes the IRankDataAccess object.
     */
    public RankManager() {
        dao = new RankDBAccess();
    }

    /**
     * Get all ranks from the database or returns them if already loaded.
     * @return A list of all ranks.
     * @throws ReadRankException If an error occurs during the fetching of rank data.
     */
    public ArrayList<Rank> getAllRanks() throws ReadRankException {
        if (allRanks == null) {
            this.allRanks = dao.getAllRanks();
        }
        return allRanks;
    }

    /**
     * Retrieves a specific rank by its ID.
     * @param id The ID of the rank to retrieve.
     * @return The Rank object if found, or null if not.
     * @throws ReadRankException If ranks are not yet loaded and cannot be fetched.
     */
    public Rank getRankById(int id) throws ReadRankException {
        if (allRanks == null) {
            getAllRanks();
        }
        for (Rank rank : allRanks) {
            if (rank.getRank() == id) {
                return rank;
            }
        }
        return null;
    }
}
