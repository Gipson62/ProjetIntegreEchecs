package testPackage;

import controllerPackage.RankController;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RankTest {
    private RankController rankController;
    @BeforeEach
    public void setUp() {
        rankController = new RankController();
    }
    @Test
    public void getAllRanks() {
        ArrayList<Rank> allRanks = new ArrayList<>();
        allRanks.add(new Rank(1, "Fer", "Description pour Fer"));
        allRanks.add(new Rank(2, "Bronze", "Description pour Bronze"));
        allRanks.add(new Rank(3, "Argent", "Description pour Argent"));
        allRanks.add(new Rank(4, "Or", "Description pour Or"));
        allRanks.add(new Rank(5, "Platine", "Description pour Platine"));
        allRanks.add(new Rank(6, "Diamant", "Description pour Diamant"));
        try {
            ArrayList<Rank> ranks = rankController.getAllRanks();
            for(int i = 0; i < allRanks.size(); i++) {
                assertEquals(allRanks.get(i).getRank(), ranks.get(i).getRank());
                assertEquals(allRanks.get(i).getName(), ranks.get(i).getName());
            }
        } catch (ReadRankException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getRankById() {
        try {
            assertEquals(rankController.getRankById(1).getName(), "Fer");
            assertEquals(rankController.getRankById(2).getName(), "Bronze");
            assertEquals(rankController.getRankById(3).getName(), "Argent");
            assertEquals(rankController.getRankById(4).getName(), "Or");
            assertEquals(rankController.getRankById(5).getName(), "Platine");
            assertEquals(rankController.getRankById(6).getName(), "Diamant");
        } catch (ReadRankException e) {
            throw new RuntimeException(e);
        }
    }
}