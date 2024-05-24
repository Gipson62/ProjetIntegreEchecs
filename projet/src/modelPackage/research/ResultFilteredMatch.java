package modelPackage.research;

import modelPackage.accountModel.Elo;
import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.Tag;
import modelPackage.accountModel.Username;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class ResultFilteredMatch {
    private String matchWin;
    private Username usernameWhite;
    private Tag tagWhite;
    private Elo eloWhite;

    private Elo eloBlack;
    private Username usernameBlack;
    private Tag tagBlack;
    private LocalDate dateMatch;

    public ResultFilteredMatch(String matchWin, String usernameWhite, String tagWhite, int eloWhite,
                               String usernameBlack, int eloBlack, String tagBlack , LocalDate dateMatch) throws IllegalAccountArgumentException {
        try {
            this.usernameWhite = new Username( usernameWhite);
            this.eloWhite = new Elo(eloWhite);
            this.tagWhite = new Tag(parseInt(tagWhite) );
            this.usernameBlack = new Username( usernameBlack);
            this.eloBlack = new Elo(eloBlack);
            this.tagBlack = new Tag(parseInt(tagBlack));

        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("resultFiltredMatch constructor failed: " + e.getMessage());
        }
        this.matchWin = matchWin;
        this.dateMatch = dateMatch;
    }

    public String getMatchWin() {
        return matchWin;
    }

    public int getEloWhite() {
        return eloWhite.getElo();
    }

    public String getUsernameWhite() {
        return usernameWhite.getUsername();
    }

    public int getTagWhite() {
        return tagWhite.getTag();
    }

    public int getEloBlack() {
        return eloBlack.getElo();
    }

    public String getUsernameBlack() {
        return usernameBlack.getUsername();
    }

    public int getTagBlack() {
        return tagBlack.getTag();
    }

    public LocalDate getDateMatch() {
        return dateMatch;
    }



    @Override
    public String toString() {
        //return elo username+tag  winner  username+tag  elo  date
        return  getEloWhite() + " " + getUsernameWhite() + "#" + getTagWhite() + " " + matchWin + " " + getUsernameBlack() + "#" + getTagBlack() + " " + getEloBlack() + " " + dateMatch;
    }
}
