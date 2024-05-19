package businessPackage;

import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.*;
import modelPackage.research.ResultFiltredMatch;

import java.time.LocalDate;
import java.util.ArrayList;


public class testResearch {
    public static void main(String[] args) throws ResearchDataAccessException, Exception {
        ResearchManager researchManager = new ResearchManager();

        ArrayList<MatchData> matchDataArrayList = researchManager.getMatchData(new IdAccount(1),200);

        for (MatchData matchData : matchDataArrayList) {
            System.out.println(matchData);
        }

    }
}
