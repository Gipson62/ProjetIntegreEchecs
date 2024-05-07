package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;
import java.time.LocalDate;
public class Birthdate {


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
        if(birthdate.isAfter(LocalDate.now()) || birthdate.isBefore(LocalDate.now().minusYears(100))){
            throw new IllegalAccountArgumentException("Birthdate must be before today");
        }
        this.birthdate = birthdate;
    }
}
