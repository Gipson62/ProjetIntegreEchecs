package dataAccessPackage.tournamentStateDataAcces;

import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;

import java.util.ArrayList;

public interface TournamentStateDataAcces {
    ArrayList<State> getAllStates() throws ReadTournamentStateException;
}
