package modelPackage.accountModel;
import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Password {
    private String password;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 60;

    public Password(String password) throws IllegalAccountArgumentException {

        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalAccountArgumentException {
        if(password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH){
            throw new IllegalAccountArgumentException("Mot de passe doit contenir au moins "+MIN_PASSWORD_LENGTH+" caractères et au plus "+ MAX_PASSWORD_LENGTH +" caractères.");
        }
        this.password = password;
    }

}
