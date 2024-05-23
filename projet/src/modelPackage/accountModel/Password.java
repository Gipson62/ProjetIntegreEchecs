package modelPackage.accountModel;
import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Password {
    private String password;

    public Password(String password) throws IllegalAccountArgumentException {

        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalAccountArgumentException {
        if(password.length() < 8){
            throw new IllegalAccountArgumentException("Mot de passe doit contenir au moins 8 caractères");
        }
        this.password = password;
    }

}
