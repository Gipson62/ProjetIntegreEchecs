package exceptionPackage;

public class UnknownPanel extends Exception {
    public UnknownPanel(String panelName) {
        super("Yo le panel existe pas bro " + panelName);
    }
}
