package controllerPackage;

import businessPackage.ResearchManager;
import dataAccessPackage.research.ResearchDBAccess;
import dataAccessPackage.research.ResearchDataAccess;
import exceptionPackage.research.*;
import modelPackage.research.*;
import exceptionPackage.research.*;
import modelPackage.accountModel.IdAccount;


import java.util.ArrayList;

public class ResearchController {

    ResearchManager researchManager;

    public ResearchController (){
        researchManager = new ResearchManager();
    }

    public ArrayList<ResultFiltredMatch> getFiltredMatch(FilterMatch filterMatch) throws ResearchDataAccessException{
        return researchManager.getFiltredMatch(filterMatch);
    }

    public ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException{
        return researchManager.getTournamentPlayed(filterTournamentPlayed);
    }

    public ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException{
        return researchManager.getMatchData(idAccount, nbMatchDataMax);
    }

    //no limit of matchData = milite set at 5
    public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException{
        return researchManager.getMatchData(idAccount);
    }

}
