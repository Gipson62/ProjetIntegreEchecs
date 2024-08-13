package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Elo {
    //elo entre 0 et 3000
    private static final int MIN_ELO = 0;
    private static final int MAX_ELO = 3000;
    private int elo;

    public Elo(int elo) throws IllegalAccountArgumentException{

        setElo(elo);
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(int elo) throws IllegalAccountArgumentException {
        //elo between 0 and 3000
        if (elo >= MIN_ELO && elo <= MAX_ELO) {
            this.elo = elo;
        }
        else{
            throw new IllegalAccountArgumentException("L'Elo doit etre compris entre "+MIN_ELO+" et "+ MAX_ELO);
        }
    }
}
