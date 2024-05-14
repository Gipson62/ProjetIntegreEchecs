package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Bio implements Serializable {
    private String bio;

    public Bio(String bio) throws IllegalAccountArgumentException{
        setBio(bio);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) throws IllegalAccountArgumentException {
        if(bio.length() <= 256){
            this.bio = bio;
        }
        else {
            throw new IllegalAccountArgumentException("Bio must be at most 256 characters long");
        }
    }

}
