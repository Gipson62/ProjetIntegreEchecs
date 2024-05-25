package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Rank  {
    private int rank;
    private String name;
    private String description;
    private static final int MAX_RANK = 11;
    private static final int MIN_RANK = 0;

    public Rank(int rank) throws  IllegalAccountArgumentException{

        setRank(rank);
    }

    public Rank(int rank,String name, String description) throws  IllegalAccountArgumentException{
        this.name = name;
        this.description = description;
        setRank(rank);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(Integer  rank) throws IllegalAccountArgumentException{

        //taille max 11
        if(rank > MIN_RANK && rank <= MAX_RANK){
            this.rank = rank;
        }else {
            throw new IllegalAccountArgumentException("le Rank doit etre entre "+MIN_RANK+" et "+MAX_RANK);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
