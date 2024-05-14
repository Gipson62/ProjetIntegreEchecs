package businessPackage;

import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

public class testEncore {
    public static void main(String[] args) {
        // Mot de passe correct (vous pouvez le récupérer à partir de la base de données par exemple)
        String bonMDP = "$2a$10$aY6YXJzQMVAOYNSkvJKHxO7w5seFJXKzeo6eISbVeMrMNMsuoLaCO";
        System.out.println(bonMDP);
        while (true) {
            // Demande un mot de passe à l'utilisateur avec un JOptionPane
            String password = JOptionPane.showInputDialog(null, "Veuillez entrer votre mot de passe :");

            // Si l'utilisateur clique sur Annuler, quitte la boucle
            if (password == null) {
                break;
            }

            // Vérifie si le mot de passe saisi correspond au mot de passe correct
            System.out.println("Mot de passe saisi : " + password);
            System.out.println(doHashing(password));

            boolean isCorrect = checkPassword(password, bonMDP);
            if (isCorrect) {
                System.out.println("Mot de passe correct !");
            } else {
                System.out.println("Mot de passe incorrect !");
            }
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        // Vérifie si le mot de passe saisi correspond au haché du mot de passe correct
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String doHashing(String password) {
        // Génère un haché bcrypt du mot de passe
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
