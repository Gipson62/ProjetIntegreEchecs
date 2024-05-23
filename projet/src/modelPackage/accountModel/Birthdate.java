package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;
import java.time.LocalDate;
public class Birthdate  {

    private static final int MAX_AGE = 120;

    private LocalDate birthdate;
    //must be before today
    public Birthdate(LocalDate birthdate) throws IllegalAccountArgumentException {

        setBirthdate(birthdate);
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) throws IllegalAccountArgumentException {
        //must be before today and after today - 100 years
        if(birthdate.isAfter(LocalDate.now()) || birthdate.isBefore(LocalDate.now().minusYears(MAX_AGE))){
            throw new IllegalAccountArgumentException("Date de naissance invalide : " + birthdate + " doit être avant aujourd'hui et après " + LocalDate.now().minusYears(MAX_AGE) );
        }
        this.birthdate = birthdate;
    }
}
