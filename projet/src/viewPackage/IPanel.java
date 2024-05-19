package viewPackage;

public interface IPanel {
    /**
     * Function used to put a panel back to its original state (used everytime we switch from one panel to another
     */
    public void resetPanel();
    public void init();
}
