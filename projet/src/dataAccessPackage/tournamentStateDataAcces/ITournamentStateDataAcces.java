package dataAccessPackage.tournamentStateDataAcces;

import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;

import java.util.ArrayList;

/**
 * Interface for accessing tournament state data from a storage system.
 */
public interface ITournamentStateDataAcces {
    /**
     * Retrieves all possible states of a tournament.
     *
     * @return A list of all tournament states.
     * @throws ReadTournamentStateException If there is a problem accessing the data.
     */
    ArrayList<State> getAllStates() throws ReadTournamentStateException;
}
