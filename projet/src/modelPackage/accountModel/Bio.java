package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Bio  {
    private String bio;
    private static final int MAX_BIO = 512;

    public Bio(String bio) throws IllegalAccountArgumentException{
        setBio(bio);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) throws IllegalAccountArgumentException {
        if(!(bio.length() <= MAX_BIO) ){
            throw new IllegalAccountArgumentException("La Bio est trop longue!"+ MAX_BIO +" caractères maximum");
        }
        //bio can't be empty or can't be spaces and \n
        if (bio.isEmpty() || bio.matches("[ \\n]*")){
            throw new IllegalAccountArgumentException("La Bio ne peut pas être vide");
        }


        this.bio = bio;
    }


}
