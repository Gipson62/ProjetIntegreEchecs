package businessPackage;

import dataAccessPackage.research.ResearchDBAccess;
import dataAccessPackage.research.ResearchDataAccess;
import exceptionPackage.research.*;
import modelPackage.research.*;
import exceptionPackage.research.*;
import modelPackage.accountModel.IdAccount;


import java.util.ArrayList;

public class ResearchManager {

        ResearchDataAccess dao;

        public ResearchManager(){
            dao = new ResearchDBAccess();
        }

        public ArrayList<ResultFiltredMatch> getFiltredMatch(FilterMatch filterMatch) throws ResearchDataAccessException{
            return dao.getFiltredMatch(filterMatch);
        }

        public ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException{
            return dao.getTournamentPlayed(filterTournamentPlayed);
        }

        public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException{
            return dao.getMatchData(idAccount);
        }

}
