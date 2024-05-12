package modelPackage.statistic;

public class MovementData {
    private String name;
    private int winWith;
    private int loseWith;
    private int winAgainst;
    private int loseAgainst;
    private Double winRateWith;
    private Double winRateAgainst;

    public MovementData(String name) {
        this.name = name;
        this.winWith = 0;
        this.loseWith = 0;
        this.winAgainst = 0;
        this.loseAgainst = 0;
        this.winRateWith = null;
        this.winRateAgainst = null;
    }

    public void calculateStat() {
        if (winWith + loseWith != 0) {
            winRateWith = (double) (winWith / (winWith + loseWith))*100;
        }
        if (winAgainst + loseAgainst != 0) {
            winRateAgainst = (double) (winAgainst / (winAgainst + loseAgainst))*100;
        }

    }

    public double getWinRateWith() {
        return winRateWith;
    }

    public double getWinRateAgainst() {
        return winRateAgainst;
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
                ", winWith=" + winWith +
                ", loseWith=" + loseWith +
                ", winAgainst=" + winAgainst +
                ", loseAgainst=" + loseAgainst +
                ", winRateWith=" + (winRateWith == null ? "?":  winRateWith)+
                ", winRateAgainst=" + (winRateAgainst == null ? "?": winRateAgainst)+
                '}';
    }
}
