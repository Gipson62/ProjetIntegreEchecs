package businessPackage;

import dataAccessPackage.research.ResearchDBAccess;
import dataAccessPackage.research.ResearchDataAccess;
import exceptionPackage.research.*;
import modelPackage.research.*;
import exceptionPackage.research.*;


import java.util.ArrayList;

public class ResearchManager {

        ResearchDataAccess dao;

        public ResearchManager(){
            dao = new ResearchDBAccess();
        }

        public ArrayList<ResultFiltredMatch> getFiltredMatch(FilterMatch filterMatch) throws ResearchDataAccessException{
            return dao.getFiltredMatch(filterMatch);
        }

}
