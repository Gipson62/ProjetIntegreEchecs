package businessPackage;

import dataAccessPackage.research.IResearchDataAccess;
import dataAccessPackage.research.IResearchDBAccess;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.research.*;
import modelPackage.research.*;
import modelPackage.accountModel.IdAccount;


import java.util.ArrayList;

/**
 * Manages research operations, serving as a facade for interaction with the research data layer.
 */
public class ResearchManager {

        IResearchDataAccess dao;

    /**
     * Constructor that initializes the IResearchDataAccess implementation.
     */
        public ResearchManager(){
            dao = new IResearchDBAccess();
        }


    /**
     * Retrieves a list of filtered match results based on specified criteria.
     *
     * @param filterMatch The filtering criteria for matches.
     * @return A list of results that match the specified filters.
     * @throws ResearchDataAccessException If there is an issue retrieving the data.
     */

        public ArrayList<ResultFilteredMatch> getFilteredMatch(FilterMatch filterMatch) throws ResearchDataAccessException{
            return dao.getFilteredMatch(filterMatch);
        }

    /**
     * Retrieves a list of tournaments played based on specified filtering criteria.
     *
     * @param filterTournamentPlayed The filtering criteria for tournaments played.
     * @return A list of tournaments that match the specified filters.
     * @throws ResearchDataAccessException If there is an issue retrieving the data.
     */
        public ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException{
            return dao.getTournamentPlayed(filterTournamentPlayed);
        }

    /**
     * Retrieves a list of match data for a given account, limited by a specified maximum number of matches.
     *
     * @param idAccount The account ID for which match data is requested.
     * @param nbMatchDataMax The maximum number of match data entries to retrieve.
     * @return A list of MatchData objects up to the specified limit.
     * @throws ResearchDataAccessException If the account does not exist or there is an error during data retrieval.
     */
        public ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException{
            try {
                AccountManager accountManager = new AccountManager();
                if (accountManager.getAccount(idAccount) == null) throw new ResearchDataAccessException("Le compte demandé n'existe pas");
            } catch (ReadAccountException e) {
                throw new ResearchDataAccessException("Erreur lors de la recherche du compte");
            }
            return dao.getMatchData(idAccount, nbMatchDataMax);
        }

        //no limit of matchData = milite set at 20
        public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException{
            return dao.getMatchData(idAccount,20);
        }

}
