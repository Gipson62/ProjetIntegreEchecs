package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Gender {
    //string de taille 6 max fait que de lettre
    private String gender ;

    public Gender (String gender) throws IllegalAccountArgumentException{
        setGender(gender);
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender) throws IllegalAccountArgumentException{
        if(gender.length() <= 16 ){
            if (!gender.isEmpty()){this.gender = gender;}
            else {
                throw new IllegalAccountArgumentException("Le champ Genre ne peut pas être vide");
            }
        }else {
            throw new IllegalAccountArgumentException("Le champ Genre doit contenir au maximum 16 caractères");
        }
    }

    public String toString(){
        return "Gender : " +this.gender ;
    }
}

