package modelPackage.accountModel;

import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

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
                   String password, String bio, Integer tag, boolean isBeginner, Rank rank,
                   int elo, String gender) throws IllegalAccountArgumentException {
        List<String> errors = new ArrayList<>();

        validateField("ID Account", idAccount, errors);
        validateField("Username", username, errors);
        validateField("Email", email, errors);
        validateField("Birthdate", birthdate, errors);
        validateField("Password", password, errors);
        validateField("Bio", bio, errors);
        validateField("Tag", tag, errors);
        this.isBeginner = isBeginner;
        this.rank = rank;
        validateField("Elo", elo, errors);
        validateField("Gender", gender, errors);

        if (!errors.isEmpty()) {
            String errorMessage = String.join("\n", errors);
            throw new IllegalAccountArgumentException(errorMessage);
        }
    }

    private void validateField(String fieldName, Object value, List<String> errors) {
        try {
            switch (fieldName) {
                case "ID Account":
                    setIdAccount((Integer) value);
                    break;
                case "Username":
                    setUsername((String) value);
                    break;
                case "Email":
                    setEmail((String) value);
                    break;
                case "Birthdate":
                    setBirthdate((LocalDate) value);
                    break;
                case "Password":
                    setPassword((String) value);
                    break;
                case "Bio":
                    setBio((String) value);
                    break;
                case "Tag":
                    setTag((Integer) value);
                    break;
                case "Elo":
                    setElo((Integer) value);
                    break;
                case "Gender":
                    setGender((String) value);
                    break;
                default:
                    System.out.println("Invalid field name");
            }
        } catch (IllegalAccountArgumentException e) {
            errors.add(fieldName + ": " + e.getMessage());
        } catch (ClassCastException | IllegalArgumentException e) {
            System.out.println(fieldName + " est invalide : " + value + " n'est pas du bon type");
        }
    }


    //Setter
    public void setIdAccount(Integer idAccount) throws IllegalAccountArgumentException {
        this.idAccount = idAccount == null ? null : new IdAccount(idAccount);
    }

    public void setUsername(String username) throws IllegalAccountArgumentException {
        if (username == null) {
            throw new IllegalAccountArgumentException("Pseudo ne peut pas être vide");
        }
        this.username = new Username(username);
    }

    public void setEmail(String email) throws IllegalAccountArgumentException {
        if (email == null) {
            throw new IllegalAccountArgumentException("Email ne peut pas être vide");
        }
        this.email = new Email(email);
    }

    public void setBirthdate(LocalDate birthdate) throws IllegalAccountArgumentException {
        if (birthdate == null) {
            throw new IllegalAccountArgumentException("Date de naissance ne peut pas être vide");
        }
        this.birthdate = new Birthdate(birthdate);
    }

    public void setPassword(String password) throws IllegalAccountArgumentException {
        if (password == null) {
            throw new IllegalAccountArgumentException("Mot de passe ne peut pas être vide");
        }
        this.password = new Password(password);
    }

    public void setBio(String bio) throws IllegalAccountArgumentException {
        this.bio = bio == null ? null : new Bio(bio);
    }

    public void setTag(Integer tag) throws IllegalAccountArgumentException {
        this.tag = tag == null ? null :new Tag(tag);
    }

    public void setIsBeginner(Boolean isBeginner) {
        this.isBeginner = isBeginner;
    }

    public void setRank(int rank, String name, String description) throws IllegalAccountArgumentException {
        this.rank = new Rank(rank , name, description);
    }

    public void setRank(Rank rank) throws IllegalAccountArgumentException {
        this.rank = rank;
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

    public Integer getTag() {
        return tag == null ? null :tag.getTag();
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
