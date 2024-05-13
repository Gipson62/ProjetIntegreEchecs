package modelPackage.research;

import java.util.ArrayList;
import modelPackage.accountModel.Username;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.IllegalAccountArgumentException;



public class MatchData {
    private final Username player;
    private final Username opponent;
    private final int match_id;
    private final String [] moves;
    private final String attack;
    private final String defense;
    private final String Opening;
    private final char result;
    private final String winOrLose;

    public MatchData(Username player, Username opponent, int match_id, String []moves, String attack, String defense, String Opening, char result, String winOrLose) throws IllegalArgumentException {
        if (player == null || opponent == null || match_id < 0 || moves == null || attack == null || defense == null || Opening == null || result == ' ' || winOrLose == null) {
            throw new IllegalArgumentException("Illegal argument in MatchData");
        }
        this.player = player;
        this.opponent = opponent;
        this.match_id = match_id;
        this.moves = moves;
        this.attack = attack;
        this.defense = defense;
        this.Opening = Opening;
        this.result = result;
        this.winOrLose = winOrLose;
    }

    public MatchData(String player, String opponent, int match_id, String []moves, String attack, String defense, String Opening, char result, String winOrLose) throws IllegalAccountArgumentException {
        if (player == null || opponent == null || match_id < 0 || moves == null || attack == null || defense == null || Opening == null || result == ' ' || winOrLose == null) {
            throw new IllegalAccountArgumentException("Illegal argument in MatchData");
        }
        this.player = new Username(player);
        this.opponent = new Username(opponent);
        this.match_id = match_id;
        this.moves = moves;
        this.attack = attack;
        this.defense = defense;
        this.Opening = Opening;
        this.result = result;
        this.winOrLose = winOrLose;

    }

    public String getPlayer() {
        return player.getUsername();
    }

    public String getOpponent() {
        return opponent.getUsername();
    }

    public int getMatch_id() {
        return match_id;
    }

    public String getMoves() {
        return String.join("=>", moves);
    }

    public String getAttack() {
        return attack;
    }

    public String getDefense() {
        return defense;
    }

    public String getOpening() {
        return Opening;
    }

    public char getResult() {
        return result;
    }

    public String getWinOrLose() {
        return winOrLose;
    }

    public String toString() {
        return "MatchData{" +
                "player=" + player.getUsername() +
                ", opponent=" + opponent.getUsername() +
                ", match_id=" + match_id +
                ", moves=" + getMoves() +
                ", attack=" + attack +
                ", defense=" + defense +
                ", Opening=" + Opening +
                ", winner color=" + result +
                ", winOrLose=" + winOrLose +
                '}';
    }
}
