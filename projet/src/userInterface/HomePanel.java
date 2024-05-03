package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends DefaultPanel {
    MainWindow mainWindow;
    public HomePanel(MainWindow initMainWindow) {
        this.mainWindow = initMainWindow;

        // TODO : Add a button to transition to "login" or "signUp" if it's not already done
        JButton button = new JButton("Salut");
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BROOOOO?");
                mainWindow.changePanel("LoginPanel");
            }
        });
    }

    @Override
    public void resetPanel() {
        return;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Yo ça va les gars? ça va être rempli plus tard", 25, 25);
    }
}
