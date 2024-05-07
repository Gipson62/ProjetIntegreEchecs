package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;
public class Rank {
    private String rank;

    public Rank(String rank) throws  IllegalAccountArgumentException{

        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) throws IllegalAccountArgumentException{

        //taille max 11
        if(rank.length() < 11 && rank.length() > 0){
            this.rank = rank;
        }else {
            throw new IllegalAccountArgumentException("Wrong rank size");
        }
    }
}
