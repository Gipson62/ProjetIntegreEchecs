package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Gender {
    private String gender ;
    private static final int MAX_LENGTH = 16;

    public Gender (String gender) throws IllegalAccountArgumentException{
        setGender(gender);
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender) throws IllegalAccountArgumentException{
        if(gender.length() <= MAX_LENGTH ){
            if (!gender.isEmpty()){this.gender = gender;}
            else {
                throw new IllegalAccountArgumentException("Le champ Genre ne peut pas être vide");
            }
        }else {
            throw new IllegalAccountArgumentException("Le champ Genre doit contenir au maximum "+MAX_LENGTH+" caractères");
        }
    }

    public String toString(){
        return "Gender : " +this.gender ;
    }
}

