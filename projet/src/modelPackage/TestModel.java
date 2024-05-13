package modelPackage;

import java.time.LocalDate;
import modelPackage.accountModel.*;
import exceptionPackage.IllegalAccountArgumentException;
public class TestModel {

    public static void main(String[] args) {
        System.out.println("TestModel (Account)\n");


        try {
            // Création d'une instance de Account
            Account account = new Account(
                    null, // idAccount
                    "E", // username
                    "john.doe@example.com", // email
                    LocalDate.of(1990, 5, 15), // birthdate
                    "123456789", // password
                    null, // bio
                    -30, // tag
                    true, // isBeginner
                    new Rank(1), // rank
                    -1, // elo
                    "batman" // gender
            );

            // Affichage des informations de l'Account
            System.out.println("ID: " + account.getIdAccount());
            System.out.println("Username: " + account.getUsername());
            System.out.println("Email: " + account.getEmail());
            System.out.println("Birthdate: " + account.getBirthdate());
            System.out.println("Password: " + account.getPassword());
            System.out.println("Bio: " + account.getBio());
            System.out.println("Tag: " + account.getTag());
            System.out.println("Is beginner: " + account.getIsBeginner());
            System.out.println("Rank: " + account.getRank());
            System.out.println("Elo: " + account.getElo());
            System.out.println("Gender: " + account.getGender());

        } catch (IllegalAccountArgumentException e) {
            System.out.println("Error creating account :\n" + e.getMessage());
        }
    }
}
