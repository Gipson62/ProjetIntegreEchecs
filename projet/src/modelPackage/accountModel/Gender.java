package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

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
        if(gender.length() <= 6 && gender.length() > 0){
            this.gender = gender;
        }else {
            throw new IllegalAccountArgumentException("Wrong gender size");
        }
    }

    public String toString(){
        return "Gender : " +this.gender ;
    }
}
