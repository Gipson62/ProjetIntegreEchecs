package dataAccessPackage.rankDataAccess;

import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;
import java.util.ArrayList;

/**
 * Interface defining the data access operations related to ranks.
 */
public interface IRankDataAccess {
    /**
     * Retrieves all rank entries from the data store.
     * @return A list of Rank objects representing all ranks.
     * @throws ReadRankException If there is an error during database access.
     */
    ArrayList<Rank> getAllRanks() throws ReadRankException;
}
