package businessPackage;

public class testRank {
    public static void main(String[] args) {
        System.out.println("Test Rank");
        RankManager rankManager = new RankManager();
        try {
            System.out.println(rankManager.getAllRanks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
