package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Bio  {
    private String bio;

    public Bio(String bio) throws IllegalAccountArgumentException{
        setBio(bio);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) throws IllegalAccountArgumentException {
        if(bio.length() <= 512){
            this.bio = bio;
        }
        else {
            throw new IllegalAccountArgumentException("La Bio est trop longue! 512 caractères maximum");
        }
    }

}
