package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.research.FilterMatch;
import modelPackage.research.ResultFiltredMatch;


public class testResearch {
    public static void main(String[] args) throws ResearchDataAccessException, Exception {
        ResearchManager researchManager = new ResearchManager();
        FilterMatch filterMatch = new FilterMatch(1,1500,"2024-07-01","2024-07-31");
        System.out.println(researchManager.getFiltredMatch(filterMatch));
    }
}
