package modelPackage.research;

import modelPackage.accountModel.IdAccount;
import modelPackage.accountModel.Elo;
import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.Tag;
import modelPackage.accountModel.Username;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class ResultFiltredMatch {
    private boolean matchWin;
    private Username opponentUsername;
    private Tag tagOpponent;
    private Elo eloOpponent;
    private LocalDate dateMatch;

    public ResultFiltredMatch(boolean matchWin, String opponentUsername,String tagOpponent, int eloOpponent, LocalDate dateMatch) throws IllegalAccountArgumentException {
        try {
            this.opponentUsername = new Username(opponentUsername);
            this.eloOpponent = new Elo(eloOpponent);
            this.tagOpponent = new Tag(parseInt(tagOpponent) );
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("resultFiltredMatch constructor failed: " + e.getMessage());
        }
        this.matchWin = matchWin;
        this.dateMatch = dateMatch;
    }

    public Elo getEloOpponent() {
        return eloOpponent;
    }

    public String getOpponentId() {
        return opponentUsername.getUsername();
    }

    public boolean isMatchWin() {
        return matchWin;
    }

    public LocalDate getDateMatch() {
        return dateMatch;
    }


    @Override
    public String toString() {
        return "ResultFiltredMatch{" +
                "matchWin=" + matchWin +
                ", opponentUsername=" + opponentUsername.getUsername() +
                ", eloOpponent=" + eloOpponent.getElo() +
                ", dateMatch=" + dateMatch +
                '}';
    }
}
