package modelPackage.research;

import modelPackage.accountModel.Elo;
import modelPackage.accountModel.Username;
import exceptionPackage.IllegalAccountArgumentException;

import java.time.LocalDate;

public class ResultTournamentPlayed {
    private String nameTournament;
    private LocalDate dateMatch;// date des match jouer pas du tournoi
    private Elo tournamentElo;
    private int timeMatch;
    private Username userTournamentWinner;

    public ResultTournamentPlayed(String nameTournament, LocalDate dateMatch, Elo tournamentElo, int timeMatch, Username userTournamentWinner) throws IllegalArgumentException {

        if (nameTournament == null || nameTournament.isEmpty()) {
            throw new IllegalArgumentException("nameTournament is null or empty");
        }
        if (dateMatch == null || dateMatch.toString().isEmpty()) {
            throw new IllegalArgumentException("dateMatch is null or empty");
        }
        if (timeMatch < 0) {
            throw new IllegalArgumentException("timeMatch is negative");
        }
        if (userTournamentWinner == null || userTournamentWinner.toString().isEmpty()) {
            throw new IllegalAccountArgumentException("userTournamentWinner is null or empty");
        }
        if (tournamentElo == null) {
            throw new IllegalAccountArgumentException("tournamentElo is null");
        }

        this.nameTournament = nameTournament;
        this.dateMatch = dateMatch;
        this.tournamentElo = tournamentElo;
        this.timeMatch = timeMatch;
        this.userTournamentWinner = userTournamentWinner;
    }

    public ResultTournamentPlayed(String nameTournament, LocalDate dateMatch, int tournamentElo, int timeMatch, String userTournamentWinner) throws IllegalAccountArgumentException {
        this(nameTournament, dateMatch, new Elo(tournamentElo), timeMatch, new Username(userTournamentWinner));
    }

    public String getNameTournament() {
        return nameTournament;
    }

    public LocalDate getDateMatch() {
        return dateMatch;
    }

    public int getTournamentElo() {
        return tournamentElo.getElo();
    }

    public int getTimeMatch() {
        return timeMatch;
    }

    public String getUserTournamentWinner() {
        return userTournamentWinner.getUsername();
    }

    @Override
    public String toString() {
        return "ResultTournamentPlayed{" +
                "nameTournament='" + nameTournament + '\'' +
                ", dateMatch=" + dateMatch +
                ", tournamentElo=" + tournamentElo +
                ", timeMatch=" + timeMatch +
                ", userTournamentWinner=" + userTournamentWinner +
                '}';
    }
}
