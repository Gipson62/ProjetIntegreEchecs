package businessPackage;

import dataAccessPackage.tournamentStateDataAcces.TournamentStateDBAcces;
import dataAccessPackage.tournamentStateDataAcces.TournamentStateDataAcces;
import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;
import java.util.ArrayList;

public class TournamentStateManager {
    TournamentStateDataAcces dao;

    public TournamentStateManager(){
        dao = new TournamentStateDBAcces();
    }

    public ArrayList<State> getAllStates() throws ReadTournamentStateException{
        return dao.getAllStates();
    }
}
