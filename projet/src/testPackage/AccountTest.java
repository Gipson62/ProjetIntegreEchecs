package testPackage;

import controllerPackage.AccountController;
import controllerPackage.RankController;
import exceptionPackage.account.AddAccountException;
import exceptionPackage.account.DeleteAccountLignesException;
import exceptionPackage.account.ReadAccountException;
import exceptionPackage.account.UpdateAccountException;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Account;
import modelPackage.accountModel.IdAccount;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private AccountController accountController;
    private RankController rankController;
    @BeforeEach
    public void setUp() {
        this.accountController = new AccountController();
        this.rankController = new RankController();
    }
    @Test
    public void createAccountTest() {
        try {
            Account newAccount = new Account(
                    null,
                    "Gipson62",
                    "j.h.gipson62@gmail.com",
                    LocalDate.of(2004, 1, 6),
                    "Gipson62#8015",
                    "Salut je m'appelle Julien",
                    null,
                    true,
                    rankController.getRankById(1),
                    400,
                    "Male"
            );
            accountController.addAccount(newAccount);
            assertThrows(AddAccountException.class, () -> {
                accountController.addAccount(newAccount);
            });
        } catch (ReadRankException | AddAccountException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void readAccountTest() {

    }
    @Test
    public void CRUDTest() {
        setUp();
        try {
            //CREATE
            Account newAccount = new Account(
                null,
                "Gipson62",
                "j.h.gipson62@gmail.com",
                LocalDate.of(2004, 1, 6),
                "Gipson62#8015",
                "Salut je m'appelle Julien",
                null,
                true,
                rankController.getRankById(1),
                400,
                "Male"
            );
            accountController.addAccount(newAccount);
            assertThrows(AddAccountException.class, () -> {
                accountController.addAccount(newAccount);
            });

            //READ
            assertEquals(newAccount.getIdAccount(), accountController.getAccount(newAccount.getEmail()).getIdAccount());
            assertEquals(newAccount.getUsername(), accountController.getAccount(newAccount.getEmail()).getUsername());
            assertEquals(newAccount.getEmail(), accountController.getAccount(newAccount.getEmail()).getEmail());
            assertEquals(newAccount.getTag(), accountController.getAccount(newAccount.getEmail()).getTag());

            //UPDATE
            newAccount.setBio(null); //Remove the bio
            newAccount.setGender(null);
            newAccount.setUsername("Gipson");
            newAccount.setElo(1500);
            newAccount.setRank(rankController.getRankById(4));
            accountController.updateAccount(newAccount);
            assertEquals(newAccount.getIdAccount(), accountController.getAccount(newAccount.getEmail()).getIdAccount());
            assertEquals(newAccount.getUsername(), accountController.getAccount(newAccount.getEmail()).getUsername());
            assertEquals(newAccount.getEmail(), accountController.getAccount(newAccount.getEmail()).getEmail());
            assertEquals(newAccount.getTag(), accountController.getAccount(newAccount.getEmail()).getTag());
            assertEquals(newAccount.getBio(), accountController.getAccount(newAccount.getEmail()).getBio());
            assertEquals(newAccount.getUsername(), accountController.getAccount(newAccount.getEmail()).getUsername());
            assertEquals(newAccount.getGender(), accountController.getAccount(newAccount.getEmail()).getGender());
            assertEquals(newAccount.getElo(), accountController.getAccount(newAccount.getEmail()).getElo());
            assertEquals(newAccount.getRank(), accountController.getAccount(newAccount.getEmail()).getRank());

            //DELETE
            ArrayList<Integer> idAccounts = new ArrayList<>();
            idAccounts.add(newAccount.getIdAccount());
            accountController.deleteAccountLignes(idAccounts);
            assertEquals(null, accountController.getAccount(newAccount.getIdAccountO()));
        } catch (ReadRankException | AddAccountException | UpdateAccountException | ReadAccountException |
                 DeleteAccountLignesException e) {
            throw new RuntimeException(e);
        }
    }
}
