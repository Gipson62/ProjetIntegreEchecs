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
            setIdAccount1(idAccount1);
            setIdAccount2(idAccount2);
            this.dateFriendship = null;
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException(e.getMessage() +"(Friendship constructor failed)");
        }
    }
    public Friendship(IdAccount idAccount1, IdAccount idAccount2) {
            this.idFriendship = null;
            this.idAccount1 = idAccount1;
            this.idAccount2 = idAccount2;
            this.dateFriendship = null;
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

    public Integer getIdFriendship() {
        return idFriendship;
    }

    public int getIdAccount1() {
        return idAccount1.getIdAccount();
    }

    public int getIdAccount2() {
        return idAccount2.getIdAccount();
    }

    public void setIdAccount1(int idAccount1) throws IllegalAccountArgumentException{
        this.idAccount1 = new IdAccount(idAccount1);
    }

    public void setIdAccount2(int idAccount2) throws IllegalAccountArgumentException{
        this.idAccount2 = new IdAccount(idAccount2);
    }

    public void setDateFriendship(LocalDate dateFriendship) {
        this.dateFriendship = dateFriendship;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "idFriendship=" + idFriendship +
                ", idAccount1=" + idAccount1 +
                ", idAccount2=" + idAccount2 +
                ", dateFriendship=" + dateFriendship +
                '}';
    }
}
