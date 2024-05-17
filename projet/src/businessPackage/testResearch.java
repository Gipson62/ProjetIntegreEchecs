package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.*;
import modelPackage.research.ResultFiltredMatch;

import java.time.LocalDate;


public class testResearch {
    public static void main(String[] args) throws ResearchDataAccessException, Exception {
        ResearchManager researchManager = new ResearchManager();

        //TODO : mantch win remplacer par String avec 'win' ou 'lose'

        FilterMatch filterMatch = new FilterMatch(1500,"2024-07-01","2024-07-31");
        System.out.println(researchManager.getFiltredMatch(filterMatch));

        //test de la recherche de tournois joués

    }
}
