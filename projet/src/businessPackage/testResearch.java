package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.research.*;
import modelPackage.research.ResultFiltredMatch;

import java.time.LocalDate;


public class testResearch {
    public static void main(String[] args) throws ResearchDataAccessException, Exception {
        //TODO : mantch win remplacer par String avec 'win' ou 'lose'
        ResearchManager researchManager = new ResearchManager();
        FilterMatch filterMatch = new FilterMatch(1,1500,"2024-07-01","2024-07-31");
        System.out.println(researchManager.getFiltredMatch(filterMatch));

        //test de la recherche de tournois joués
        FilterTournamentPlayed filterTournamentPlayed = new FilterTournamentPlayed(1,"demi-final", LocalDate.of(2024,7,1));
        for (ResultTournamentPlayed resultTournamentPlayed : researchManager.getTournamentPlayed(filterTournamentPlayed)){
            System.out.println(resultTournamentPlayed);
        }
    }
}
