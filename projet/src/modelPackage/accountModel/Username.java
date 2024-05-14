package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Username implements Serializable {
    private String username;

    public Username(String username) throws IllegalAccountArgumentException{

        setUsername(username);
    }

    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username)  throws IllegalAccountArgumentException{
        if ( username.matches("^[a-zA-Z0-9]*$") && !username.isEmpty() && username.length() <= 24 ){
            this.username = username;
        }
        else{
            throw new IllegalAccountArgumentException("Username must contain only letters and numbers");
        }

    }
}
