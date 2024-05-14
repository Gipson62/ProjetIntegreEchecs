package dataAccessPackage.research;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.research.*;
import modelPackage.accountModel.IdAccount;
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

    @Override
    public ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException {
        ArrayList<MatchData> matchData = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "SELECT \n" +
                    "    player.username AS Toi, \n" +
                    "    opponent.username AS Adversaire, \n" +
                    "    m.id AS match_id, \n" +
                    "    move1.id AS Coup_1, \n" +
                    "    move2.id AS Coup_2, \n" +
                    "    move3.id AS Coup_3, \n" +
                    "    move4.id AS Coup_4, \n" +
                    "    attack.name AS Attaque, \n" +
                    "    attack.move1 AS ID, \n" +
                    "    defense.name AS Defence, \n" +
                    "    defense.id AS DefenceID, \n" +
                    "    opening.name AS Ouverture, \n" +
                    "    opening.id AS OuvertureID, \n" +
                    "    m.winner AS winner_color,\n" +
                    "    CASE \n" +
                    "        WHEN player.id = m.player_white AND m.winner = 'w' THEN 'Win'\n" +
                    "        WHEN player.id = m.player_white AND m.winner = 'b' THEN 'Lose'\n" +
                    "        WHEN player.id = m.player_black AND m.winner = 'b' THEN 'Win'\n" +
                    "        WHEN player.id = m.player_black AND m.winner = 'w' THEN 'Lose'\n" +
                    "        ELSE 'Unknown'\n" +
                    "    END AS MatchResult\n" +
                    "FROM \n" +
                    "    Account AS player \n" +
                    "INNER JOIN \n" +
                    "    `match` AS m ON ((player.id = m.player_white ) OR (player.id = m.player_black ))\n" +
                    "INNER JOIN \n" +
                    "    Account AS opponent ON ((player.id = m.player_white AND opponent.id = m.player_black) OR (player.id = m.player_black AND opponent.id = m.player_white))\n" +
                    "INNER JOIN \n" +
                    "    matchMove AS mM ON mM.match_id = m.id\n" +
                    "INNER JOIN \n" +
                    "    move AS move1 ON move1.id = mM.move_id AND mM.move_number = 1\n" +
                    "INNER JOIN \n" +
                    "    matchMove AS mM2 ON mM2.match_id = m.id\n" +
                    "INNER JOIN \n" +
                    "    move AS move2 ON move2.id = mM2.move_id AND mM2.move_number = 2\n" +
                    "INNER JOIN \n" +
                    "    matchMove AS mM3 ON mM3.match_id = m.id\n" +
                    "INNER JOIN \n" +
                    "    move AS move3 ON move3.id = mM3.move_id AND mM3.move_number = 3\n" +
                    "INNER JOIN \n" +
                    "    matchMove AS mM4 ON mM4.match_id = m.id\n" +
                    "INNER JOIN \n" +
                    "    move AS move4 ON move4.id = mM4.move_id AND mM4.move_number = 4\n" +
                    "LEFT JOIN \n" +
                    "    attack ON move1.id = attack.move1\n" +
                    "LEFT JOIN \n" +
                    "    defense ON defense.move1 = move1.id AND defense.move2 = move2.id\n" +
                    "LEFT JOIN \n" +
                    "    opening ON opening.move1 = move1.id AND opening.move2 = move2.id AND opening.move3 = move3.id AND opening.move4 = move4.id\n" +
                    "WHERE \n" +
                    "    player.id = ?\n" +
                    "ORDER BY \n" +
                    "    m.start_date DESC\n" +
                    "LIMIT \n" +
                    "    ?;");

            preparedStatement.setInt(1, idAccount.getIdAccount());
            preparedStatement.setInt(2, nbMatchDataMax);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String player = resultSet.getString("Toi");
                String opponent = resultSet.getString("Adversaire");
                int match_id = resultSet.getInt("match_id");
                String[] moves = {resultSet.getString("Coup_1"), resultSet.getString("Coup_2"), resultSet.getString("Coup_3"), resultSet.getString("Coup_4")};
                String attack = resultSet.getString("Attaque");
                String defense = resultSet.getString("Defence");
                String Opening = resultSet.getString("Ouverture");
                char result = resultSet.getString("winner_color").charAt(0);
                String winOrLose = resultSet.getString("MatchResult");

                try {
                    MatchData match = new MatchData(player, opponent, match_id, moves, attack, defense, Opening, result, winOrLose);
                    matchData.add(match);
                } catch (IllegalAccountArgumentException e) {
                    throw new ResearchDataAccessException("Error during the creation of the result: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new ResearchDataAccessException(" Error during the research MatchData: " + e.getMessage());
        }

        return matchData;
    }

    public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException {
        return getMatchData(idAccount, 5);
    }

}
