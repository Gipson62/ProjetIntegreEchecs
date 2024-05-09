package modelPackage;

import java.net.IDN;
import exceptionPackage.IllegalAccountArgumentException;
import java.time.LocalDate;
import modelPackage.accountModel.IdAccount;

public class Friendship {
    private Integer idFriendship;
    private IdAccount idAccount1;
    private IdAccount idAccount2;
    private LocalDate dateFriendship;

    public Friendship(int idAccount1, int idAccount2) throws IllegalAccountArgumentException{
        try {
            this.idFriendship = null;
            this.idAccount1 = new IdAccount(idAccount1);
            this.idAccount2 = new IdAccount(idAccount2);
            this.dateFriendship = LocalDate.now();
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException(e.getMessage() +"(Friendship constructor failed)");
        }
    }

    public LocalDate getDateFriendship() {
        return dateFriendship;
    }

    public void setIdFriendship(int idFriendship) throws IllegalAccountArgumentException{
        if(idFriendship < 0){
            throw new IllegalAccountArgumentException("idFriendship is null");
        }
        this.idFriendship = idFriendship;
    }
}
