package dataAccessPackage.research;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.*;

import java.util.ArrayList;

/**
 * Interface for getting research data related to matches and tournaments.
 */
public interface IResearchDataAccess {

    /**
     * Gets a list of matches that meet certain criteria.
     *
     * @param filterMatch Criteria that matches must meet to be included.
     * @return A list of filtered match results.
     * @throws ResearchDataAccessException If there's a problem getting the data.
     */
    ArrayList<ResultFilteredMatch> getFilteredMatch(FilterMatch filterMatch) throws ResearchDataAccessException;

    /**
     * Gets a list of tournaments a player has participated in, based on given criteria.
     *
     * @param filterTournamentPlayed Criteria that tournaments must meet to be included.
     * @return A list of tournaments played.
     * @throws ResearchDataAccessException If there's a problem getting the data.
     */
    ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException;

    /**
     * Gets a list of match data for a specific account, up to a maximum number of matches.
     *
     * @param idAccount The account to get match data for.
     * @param nbMatchDataMax The maximum number of matches to return.
     * @return A list of match data.
     * @throws ResearchDataAccessException If there's a problem getting the data.
     */
    ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException;

    /**
     * Gets a list of match data for a specific account, with no limit on the number of matches.
     *
     * @param idAccount The account to get match data for.
     * @return A list of match data.
     * @throws ResearchDataAccessException If there's a problem getting the data.
     */
    ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException;
}
