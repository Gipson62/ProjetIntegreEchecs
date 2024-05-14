package modelPackage.accountModel;
import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Password implements Serializable {
    private String password;

    public Password(String password) throws IllegalAccountArgumentException {

        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalAccountArgumentException {
        if(password.length() < 8){
            throw new IllegalAccountArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
    }

}
