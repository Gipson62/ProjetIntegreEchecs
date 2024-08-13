package controllerPackage;

import businessPackage.ResearchManager;
import exceptionPackage.research.*;
import modelPackage.research.*;
import modelPackage.accountModel.IdAccount;


import java.util.ArrayList;

public class ResearchController {

    ResearchManager researchManager;

    public ResearchController() {
        researchManager = new ResearchManager();
    }

    public ArrayList<ResultFilteredMatch> getFilteredMatch(FilterMatch filterMatch) throws ResearchDataAccessException {
        return researchManager.getFilteredMatch(filterMatch);
    }

    public ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException {
        return researchManager.getTournamentPlayed(filterTournamentPlayed);
    }

    public ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException {
        return researchManager.getMatchData(idAccount, nbMatchDataMax);
    }

    //no limit of matchData = milite set at 5
    public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException {
        return researchManager.getMatchData(idAccount);
    }

}
