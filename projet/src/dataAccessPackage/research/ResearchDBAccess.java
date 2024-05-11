package dataAccessPackage.research;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.research.*;
import modelPackage.research.*;
import modelPackage.research.ResultFiltredMatch;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;



public class ResearchDBAccess implements ResearchDataAccess{

    private Connection connection;

    public ResearchDBAccess(){
        connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<ResultFiltredMatch> getFiltredMatch(FilterMatch filterMatch) throws ResearchDataAccessException {
        int id = filterMatch.getUserId();
        LocalDate dateMin = filterMatch.getDateMin();
        LocalDate dateMax = filterMatch.getDateMax();
        int eloMin = filterMatch.getEloMin();

        ArrayList<ResultFiltredMatch> resultFiltredMatchs = new ArrayList<>();

        try {
            System.out.println("Start research filtred");
            PreparedStatement preparedStatement = connection.prepareStatement("select *\n" +
                    "from match_outcomes\n" +
                    "where you =?\n" +
                    "and elo2 > ?\n" +
                    "and start_date between ? and ?\n" +
                    "order by start_date ;");

            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, eloMin);
            preparedStatement.setDate(3, Date.valueOf(dateMin));
            preparedStatement.setDate(4, Date.valueOf(dateMax));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                boolean outcome = resultSet.getString("Outcome").equals("Won");
                String opponentName = resultSet.getString("Opponent");
                int elo = resultSet.getInt("elo2");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();

                try
                {
                    String[] parts = opponentName.split("#");
                    ResultFiltredMatch resultFiltredMatch = new ResultFiltredMatch(outcome, parts[0],parts[1], elo, startDate);
                    resultFiltredMatchs.add(resultFiltredMatch);
                }
                catch (IllegalAccountArgumentException e)
                {
                    throw new ResearchDataAccessException("Error during the creation of the result: " + e.getMessage());
                }
            }


        }
        catch (SQLException e){
            throw new ResearchDataAccessException(" Error during the research Matchfiltred: " + e.getMessage());
        }

        return resultFiltredMatchs;
    }

    @Override
    public ArrayList<ResultTournamentPlayed> getTournamentPlayed(FilterTournamentPlayed filterTournamentPlayed) throws ResearchDataAccessException {
        ArrayList<ResultTournamentPlayed> resultTournamentPlayeds = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select t.name,t.elo,m.start_date,m.time,winner.username\n" +
                            "from  `Match` m \n" +
                            "inner join tournament t on (m.tournament = t.id) \n" +
                            "inner join tournamentstate ts on (m.tournament_state = ts.id)\n" +
                            "inner join `Match` mFinal on (mFinal.tournament = t.id) \n" +
                            "inner join tournamentstate tsFinal on (mFinal.tournament_state = tsFinal.id and tsFinal.state = 'Final')\n" +
                            "inner join Account winner on ((mFinal.winner = 'w' and mFinal.player_white = winner.id) or (mFinal.winner = 'b' and mFinal.player_black = winner.id)) \n" +
                            "where ts.state = ?\n" +
                            "and m.start_date >= ?\n" +
                            "and (m.player_white = ? or m.player_black = ?)\n");

            preparedStatement.setString(1, filterTournamentPlayed.getTournamentStateResearch());
            preparedStatement.setDate(2, Date.valueOf(filterTournamentPlayed.getDateResearch()));
            preparedStatement.setInt(3, filterTournamentPlayed.getIdAccountResearch());
            preparedStatement.setInt(4, filterTournamentPlayed.getIdAccountResearch());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String tournamentName = resultSet.getString("name");
                int elo = resultSet.getInt("elo");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                int time = resultSet.getInt("time");
                String winner = resultSet.getString("username");

                try {
                    ResultTournamentPlayed resultTournamentPlayed = new ResultTournamentPlayed(tournamentName, startDate, elo, time, winner);
                    resultTournamentPlayeds.add(resultTournamentPlayed);
                } catch (IllegalAccountArgumentException e) {
                    throw new ResearchDataAccessException("Error during the creation of the result: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            throw new ResearchDataAccessException(" Error during the research TournamentPlayed: " + e.getMessage());
        }

        return resultTournamentPlayeds;
    }

}
