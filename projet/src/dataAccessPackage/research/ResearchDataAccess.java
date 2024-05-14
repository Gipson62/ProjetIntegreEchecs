package dataAccessPackage.research;

import exceptionPackage.research.*;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.*;
import exceptionPackage.research.*;


import java.util.ArrayList;


public interface ResearchDataAccess {

    ArrayList<ResultFiltredMatch> getFiltredMatch(FilterMatch filterMatch) throws ResearchDataAccessException;

    ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException;
    ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException;

    ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException;
}
