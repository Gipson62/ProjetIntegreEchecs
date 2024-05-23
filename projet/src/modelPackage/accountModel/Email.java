package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class Email{
    private String email;

    public Email(String email) throws IllegalAccountArgumentException {

        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalAccountArgumentException {
        if (email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            this.email = email;
        } else {
            throw new IllegalAccountArgumentException("Email invalide");
        }
    }


}
