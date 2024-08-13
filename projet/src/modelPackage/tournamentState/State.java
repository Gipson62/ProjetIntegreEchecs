package modelPackage.tournamentState;

public class State {
    private String state;
    private int idState;


    public State(int idState, String state) {
        setState(state);
        setIdState(idState);
    }
    public void setIdState(int idState) {
        this.idState = idState;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Integer getIdState() {
        return idState;
    }

    public String getState() {
        return state;
    }

    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                ", idState=" + idState +
                '}';
    }
}
