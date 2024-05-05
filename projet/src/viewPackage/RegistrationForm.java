package viewPackage;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JPanel {
    JPanel formPanel;
    JPanel buttonsPanel;
    public RegistrationForm() {
        this.setLayout(new BorderLayout());
        this.formPanel = new JPanel();
        this.add(formPanel, BorderLayout.CENTER);
    }
}
