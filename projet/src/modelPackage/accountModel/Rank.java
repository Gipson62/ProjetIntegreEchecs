package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;
public class Rank {
    private int rank;

    public Rank(int rank) throws  IllegalAccountArgumentException{

        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(Integer  rank) throws IllegalAccountArgumentException{

        //taille max 11
        if(rank >= 0 && rank <= 11){
            this.rank = rank;
        }else {
            throw new IllegalAccountArgumentException("Wrong rank size");
        }
    }
}
