package testPackage;
import controllerPackage.ResearchController;
import exceptionPackage.research.ResearchDataAccessException;
import modelPackage.accountModel.IdAccount;
import modelPackage.accountModel.Username;
import modelPackage.research.MatchData;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class ResearchTest {
    //The test only works if the database is "fresh", because it uses already known data
    private ResearchController researchController;
    @BeforeEach
    public void setUp() {
        this.researchController = new ResearchController();
    }

    @Test
    public void getMatchData()  {
        setUp();
        try{
            ArrayList<MatchData> matchesData = new ArrayList<>();
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("BobbyB"), 52, new String[]{"e4", "e5", "Nf3", "Nc6"}, "Inconnu", "Défense ouverte", "Ruy Lopez", 'b', "Win"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("JohnDoe"), 49, new String[]{"e4", "e5", "Bc4", "Nf6"}, "Inconnu", "Défense ouverte", "Italian Game", 'b', "Win"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("JohnDoe"), 47, new String[]{"d4", "Nf6", "c4", "e6"}, "Queen's Pawn Game", "Défense slave", "Indian Game", 'w', "Win"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("BobbyB"), 46, new String[]{"Nf3", "d5", "g3", "c5"}, "Ruy Lopez Attack", "Défense semi-slave", "Reti Opening", 'b', "Lose"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("AliceJ"), 44, new String[]{"d4", "d5", "c4", "c6"}, "Queen's Pawn Game", "Défense slave", "Slav Defense", 'w', "Lose"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("JohnDoe"), 15, new String[]{"e4", "e5", "Nf3", "Nc6"}, "Inconnu", "Défense ouverte", "Ruy Lopez", 'b', "Lose"));
            matchesData.add(new MatchData(new Username("JaneSmith"), new Username("JohnDoe"), 1, new String[]{"e2", "e7", "Nf3", "Nc6"}, "Inconnu", "Inconnu", "Inconnu", 'b', "Lose"));

            ArrayList<MatchData> allMatches = this.researchController.getMatchData(new IdAccount(2));
            for (int i = 0; i < allMatches.size(); i++) {
                MatchData m1 = matchesData.get(i);
                MatchData m2 = allMatches.get(i);
                assertEquals(m1.getPlayer(), m2.getPlayer());
                assertEquals(m1.getOpponent(), m2.getOpponent());
                assertEquals(m1.getMatch_id(), m2.getMatch_id());
                for (int j = 0; j < m2.getMoves().length; j++){
                    assertEquals(m1.getMoves()[j], m2.getMoves()[j]);
                }
                assertEquals(m1.getAttack(), m2.getAttack());
                assertEquals(m1.getDefense(), m2.getDefense());
                assertEquals(m1.getOpening(), m2.getOpening());
                assertEquals(m1.getResult(), m2.getResult());
                assertEquals(m1.getWinOrLose(), m2.getWinOrLose());
            }
        } catch (ResearchDataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
