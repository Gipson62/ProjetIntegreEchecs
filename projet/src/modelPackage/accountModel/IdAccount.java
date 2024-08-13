package modelPackage.accountModel;

//account exemption
import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class IdAccount {
    private int idAccount;

    public IdAccount(int idAccount) throws IllegalAccountArgumentException{

        setIdAccount(idAccount);
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) throws IllegalAccountArgumentException {
        if (idAccount < 0) {
            throw new IllegalAccountArgumentException("IdAccount must be positive");
        }
        this.idAccount = idAccount;
    }
}
