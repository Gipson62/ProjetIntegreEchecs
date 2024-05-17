package businessPackage;

public class testTournamentState {
    public static void main(String[] args) {
        TournamentStateManager tournamentStateManager = new TournamentStateManager();
        try {
            System.out.println(tournamentStateManager.getAllStates());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
