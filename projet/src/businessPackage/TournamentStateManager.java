package businessPackage;

import dataAccessPackage.tournamentStateDataAcces.ITournamentStateDBAcces;
import dataAccessPackage.tournamentStateDataAcces.ITournamentStateDataAcces;
import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;
import java.util.ArrayList;

/**
 * Manages the operations related to tournament states in the system.
 */
public class TournamentStateManager {
    private ITournamentStateDataAcces dao;

    /**
     * Constructor that initializes the data access object for managing tournament states.
     */
    public TournamentStateManager() {
        dao = new ITournamentStateDBAcces();
    }

    /**
     * Retrieves all tournament states available in the system.
     *
     * @return A list of State objects representing different possible states of a tournament.
     * @throws ReadTournamentStateException If there is an error retrieving the states from the database.
     */
    public ArrayList<State> getAllStates() throws ReadTournamentStateException {
        return dao.getAllStates();
    }
}
