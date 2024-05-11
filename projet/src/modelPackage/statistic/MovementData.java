package modelPackage.statistic;

public class MovementData {
    private String name;
    private int id;
    private String moveList;
    private int winWith;
    private int loseWith;
    private int winAgainst;
    private int loseAgainst;

    public MovementData(String name, int id, String moveList) {
        this.name = name;
        this.id = id;
        this.moveList = moveList;
        this.winWith = 0;
        this.loseWith = 0;
        this.winAgainst = 0;
        this.loseAgainst = 0;
    }

    public void addWinWith() {
        this.winWith++;
    }

    public void addLoseWith() {
        this.loseWith++;
    }

    public void addWinAgainst() {
        this.winAgainst++;
    }

    public void addLoseAgainst() {
        this.loseAgainst++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMoveList() {
        return moveList;
    }

    public int getWinWith() {
        return winWith;
    }

    public int getLoseWith() {
        return loseWith;
    }

    public int getWinAgainst() {
        return winAgainst;
    }

    public int getLoseAgainst() {
        return loseAgainst;
    }

    @Override
    public String toString() {
        return "MovementData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", moveList='" + moveList + '\'' +
                ", winWith=" + winWith +
                ", loseWith=" + loseWith +
                ", winAgainst=" + winAgainst +
                ", loseAgainst=" + loseAgainst +
                '}';
    }
}
