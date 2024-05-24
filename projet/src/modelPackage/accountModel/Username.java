package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Username {
    private String username;
    private static final int MAX_LENGTH = 24;

    public Username(String username) throws IllegalAccountArgumentException{

        setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username)  throws IllegalAccountArgumentException{
        if ( username.matches("^[a-zA-Z0-9]*$") && !username.isEmpty() && username.length() <= MAX_LENGTH ){
            this.username = username;
        }
        else{
            throw new IllegalAccountArgumentException("Pseudo ne peut contenir que des lettres et des chiffres (taille max "+MAX_LENGTH+")");
        }

    }
}
