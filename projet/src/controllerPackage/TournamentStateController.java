package controllerPackage;

import businessPackage.TournamentStateManager;
import dataAccessPackage.tournamentStateDataAcces.TournamentStateDBAcces;
import dataAccessPackage.tournamentStateDataAcces.TournamentStateDataAcces;
import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;

import java.util.ArrayList;

public class TournamentStateController {
    TournamentStateManager tournamentStateManager;

    public TournamentStateController(){
        this.tournamentStateManager = new TournamentStateManager();
    }

    public ArrayList<State> getAllStates() throws ReadTournamentStateException {
        return tournamentStateManager.getAllStates();
    }
}
