package dataAccessPackage.tournamentStateDataAcces;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.tounamentState.ReadTournamentStateException;
import modelPackage.tournamentState.State;
import java.sql.*;
import java.util.ArrayList;

public class TournamentStateDBAcces implements ITournamentStateDataAcces {

    public TournamentStateDBAcces(){ }

    @Override
    public ArrayList<State> getAllStates() throws ReadTournamentStateException {
        ArrayList<State> states = new ArrayList<>();
        try {
            Statement statement = SingletonConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TournamentState ORDER BY id ASC");
            while (resultSet.next()) {
                states.add(new State(resultSet.getInt("id"), resultSet.getString("state")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ReadTournamentStateException("Une erreur est survenue");
        }
        return states;
    }

}
