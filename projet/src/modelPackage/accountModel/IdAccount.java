package modelPackage.accountModel;

//account exemption
import exceptionPackage.IllegalAccountArgumentException;

import java.io.Serializable;

public class IdAccount implements Serializable {
    private int idAccount;

    public IdAccount(int idAccount) throws IllegalAccountArgumentException{

        setIdAccount(idAccount);
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) throws IllegalAccountArgumentException {
        //id not < 0
        if (idAccount < 0) {
            throw new IllegalAccountArgumentException("IdAccount must be positive");
        }
        this.idAccount = idAccount;
    }
}
