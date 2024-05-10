package modelPackage.research;

import modelPackage.accountModel.IdAccount;
import modelPackage.accountModel.Elo;
import exceptionPackage.IllegalAccountArgumentException;

import java.time.LocalDate;

public class FilterMatch {
    private IdAccount userId;
    private Elo eloMin;
    private LocalDate dateMin;
    private LocalDate dateMax;

    public FilterMatch(int userId, int eloMin, LocalDate dateMin, LocalDate dateMax) throws IllegalAccountArgumentException{
        try{
            this.userId = new IdAccount(userId);
            this.eloMin = new Elo(eloMin);
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("filterMatch constructor failed: " + e.getMessage());
        }
        setDate(dateMin, dateMax);
    }

    public FilterMatch(int userId, int eloMin, String dateMin, String dateMax) throws IllegalAccountArgumentException {
        this(userId, eloMin, LocalDate.parse(dateMin), LocalDate.parse(dateMax));
    }

    public int getUserId() {
        return userId.getIdAccount();
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

    public void setUserId(int userId) throws IllegalAccountArgumentException {
        this.userId.setIdAccount(userId);
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
