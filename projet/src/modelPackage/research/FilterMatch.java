package modelPackage.research;

import modelPackage.accountModel.IdAccount;
import modelPackage.accountModel.Elo;
import exceptionPackage.IllegalAccountArgumentException;

import java.time.LocalDate;

public class FilterMatch {
    private Elo eloMin;
    private LocalDate dateMin;
    private LocalDate dateMax;

    public FilterMatch( int eloMin, LocalDate dateMin, LocalDate dateMax) throws IllegalAccountArgumentException{
        try{
            this.eloMin = new Elo(eloMin);
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("filterMatch constructor failed: " + e.getMessage());
        }
        setDate(dateMin, dateMax);
    }

    public FilterMatch( int eloMin, String dateMin, String dateMax) throws IllegalAccountArgumentException {
        this( eloMin, LocalDate.parse(dateMin), LocalDate.parse(dateMax));
    }


    public int getEloMin() {
        return eloMin.getElo();
    }

    public LocalDate getDateMin() {
        return dateMin;
    }

    public LocalDate getDateMax() {
        return dateMax;
    }


    public void setEloMin(int eloMin) throws IllegalAccountArgumentException {
        this.eloMin.setElo(eloMin);
    }

    public void setDate(LocalDate dateMin, LocalDate dateMax) {
        //date min cannot be after date max
        if (dateMin.isAfter(dateMax)) {
            this.dateMin = dateMax;
            this.dateMax = dateMin;
        } else {
            this.dateMin = dateMin;
            this.dateMax = dateMax;
        }
    }
}
