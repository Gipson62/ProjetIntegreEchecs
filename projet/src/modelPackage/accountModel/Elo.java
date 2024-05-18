package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Elo implements Serializable {
    //elo entre 0 et 3000
    private int elo;

    public Elo(int elo) throws IllegalAccountArgumentException{

        setElo(elo);
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) throws IllegalAccountArgumentException {
        //elo between 0 and 3000
        if (elo >= 0 && elo <= 3000) {
            this.elo = elo;
        }
        else{
            throw new IllegalAccountArgumentException("Elo doit etre entre 0 et 3000");
        }
    }
}
