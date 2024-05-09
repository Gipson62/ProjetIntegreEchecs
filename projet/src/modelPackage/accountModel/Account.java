package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    //Attributes
    private IdAccount idAccount;
    private Username username;
    private Email email;
    private Birthdate birthdate;
    private Password password;
    private Bio bio;
    private Gender gender;
    private Tag tag;
    private boolean isBeginner;
    private Rank rank;
    private Elo elo;

    //Constructor
    public Account(Integer idAccount, String username, String email, LocalDate birthdate,
                   String password, String bio, int tag, boolean isBeginner, Integer rank,
                   int elo,String gender) throws IllegalAccountArgumentException {

        List<String> errors = new ArrayList<>();

        try {
            setIdAccount(idAccount);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setUsername(username);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setEmail(email);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setBirthdate(birthdate);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setPassword(password);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setBio(bio);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setTag(tag);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setRank(rank);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setElo(elo);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        try {
            setGender(gender);
        } catch (IllegalAccountArgumentException e) {
            errors.add(e.getMessage());
        }

        if (!errors.isEmpty()) {
            String errorMessage = String.join("\n", errors);
            throw new IllegalAccountArgumentException(errorMessage);
        }

    }

    //Setter
    public void setIdAccount(Integer idAccount) throws IllegalAccountArgumentException {
        this.idAccount = idAccount == null ? null : new IdAccount(idAccount);
    }

    public void setUsername(String username) throws IllegalAccountArgumentException {
        this.username = new Username(username);
    }

    public void setEmail(String email) throws IllegalAccountArgumentException {
        this.email = new Email(email);
    }

    public void setBirthdate(LocalDate birthdate) throws IllegalAccountArgumentException {
        this.birthdate = new Birthdate(birthdate);
    }

    public void setPassword(String password) throws IllegalAccountArgumentException {
        this.password = new Password(password);
    }

    public void setBio(String bio) throws IllegalAccountArgumentException {
        this.bio = bio == null ? null : new Bio(bio);
    }

    public void setTag(int tag) throws IllegalAccountArgumentException {
        this.tag = new Tag(tag);
    }

    public void setIsBeginner(Boolean isBeginner) {
        this.isBeginner = isBeginner;
    }

    public void setRank(Integer rank) throws IllegalAccountArgumentException {
        this.rank = new Rank(rank);
    }

    public void setElo(int elo) throws IllegalAccountArgumentException {
        this.elo = new Elo(elo);
    }

    public void setGender(String gender) throws IllegalAccountArgumentException{
        this.gender = gender == null ? null : new Gender(gender);
    }

    //Getter
    public IdAccount getIdAccountO() {
        return idAccount;
    }
    public Integer getIdAccount() {
        return idAccount == null ? null : idAccount.getIdAccount();
    }

    public Username getUsernameO() {
        return username;
    }

    public String getUsername() {
        return username.getUsername();
    }

    public Email getEmailO() {
        return email;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public Birthdate getBirthdateO() {
        return birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate.getBirthdate();
    }

    public Password getPasswordO() {
        return password;
    }

    public String getPassword() {
        return password.getPassword();
    }

    public Bio getBioO() {
        return bio;
    }

    public String getBio() {
        return bio == null ? null :bio.getBio();
    }

    public Tag getTagO() {
        return tag;
    }

    public int getTag() {
        return tag.getTag();
    }

    public Boolean getIsBeginner() {
        return isBeginner;
    }

    public Rank getRankO() {
        return rank;
    }

    public int getRank() {
        return rank.getRank();
    }

    public Elo getEloO() {
        return elo;
    }

    public int getElo() {
        return elo.getElo();
    }

    public Gender getGenderO(){
        return gender;
    }

    public String getGender(){
        return gender == null ? null :gender.getGender();
    }

    //toString
    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + getIdAccount() +
                ", username=" + getUsername() +
                ", email=" + getEmail() +
                ", birthdate=" + getBirthdate() +
                ", password=" + getPassword() +
                ", bio=" + getBio() +
                ", tag=" + getTag() +
                ", isBeginner=" + getIsBeginner() +
                ", rank=" + getRank() +
                ", elo=" + getElo() +
                ", gender=" + getGender() +
                "}";
    }
}
