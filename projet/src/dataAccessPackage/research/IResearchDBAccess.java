package dataAccessPackage.research;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.research.*;
import modelPackage.accountModel.IdAccount;
import modelPackage.research.*;
import modelPackage.research.ResultFilteredMatch;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;



public class IResearchDBAccess implements IResearchDataAccess {

    private Connection connection;

    public IResearchDBAccess(){
        connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<ResultFilteredMatch> getFilteredMatch(FilterMatch filterMatch) throws ResearchDataAccessException {

        ArrayList<ResultFilteredMatch> resultFilteredMatches = new ArrayList<>();

        try {
            System.out.println("Start research filtred");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT p2.elo AS eloWhite,CONCAT(p2.username, '#', p2.tag)as White ,\n" +
                    "        `match`.winner,\n" +
                    "        CONCAT(p1.username, '#', p1.tag) AS Black,\n" +
                    "        p1.elo AS eloBlack,\n" +
                    "        `Match`.start_date\n" +
                    "    FROM Account p1 \n" +
                    "        INNER JOIN Account p2 ON (p1.id <> p2.id) \n" +
                    "        INNER JOIN `Match` ON (p1.id = `Match`.player_black AND p2.id = `Match`.player_white)\n" +
                    "where p1.elo > ?\n" +
                    "and\tp2.elo > ?\n" +
                    "and start_date between ?and ?\n" +
                    "order by start_date ;");

            preparedStatement.setInt(1, filterMatch.getEloMin());
            preparedStatement.setInt(2, filterMatch.getEloMin());
            preparedStatement.setDate(3, Date.valueOf(filterMatch.getDateMin()));
            preparedStatement.setDate(4, Date.valueOf(filterMatch.getDateMax()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String matchWin = resultSet.getString("winner");
                String usernameWhite = resultSet.getString("White");
                String[] splitWhite = usernameWhite.split("#");
                String usernameWhiteName = splitWhite[0];
                String tagWhite = splitWhite[1];
                int eloWhite = resultSet.getInt("eloWhite");

                String usernameBlack = resultSet.getString("Black");
                String[] splitBlack = usernameBlack.split("#");
                String usernameBlackName = splitBlack[0];
                String tagBlack = splitBlack[1];
                int eloBlack = resultSet.getInt("eloBlack");

                LocalDate dateMatch = resultSet.getDate("start_date").toLocalDate();

                try {
                    ResultFilteredMatch resultFilteredMatch = new ResultFilteredMatch(matchWin, usernameWhiteName, tagWhite, eloWhite, usernameBlackName, eloBlack, tagBlack, dateMatch);
                    resultFilteredMatches.add(resultFilteredMatch);
                } catch (IllegalAccountArgumentException e) {
                    System.out.println("Une valeur de l'objet ResultFilteredMatch est incorrecte");
                }
            }


        }
        catch (SQLException e){
            throw new ResearchDataAccessException("Une erreur est survenue lors de la recherche des matchs");
        }

        return resultFilteredMatches;
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


                    ResultTournamentPlayed resultTournamentPlayed = new ResultTournamentPlayed(tournamentName, startDate, elo, time, winner);
                    resultTournamentPlayeds.add(resultTournamentPlayed);

            }
        } catch (SQLException e) {
            throw new ResearchDataAccessException("Une erreur est survenue lors de la recherche des tournois joués");
        }

        return resultTournamentPlayeds;
    }

    @Override
    public ArrayList<MatchData> getMatchData(IdAccount idAccount, int nbMatchDataMax) throws ResearchDataAccessException {
        ArrayList<MatchData> matchData = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT \n" +
                    "    player.username AS Joueur, \n" +
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
                String player = resultSet.getString("Joueur");
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
                    System.out.println("Une valeur de l'objet MatchData est incorrecte");
                }
            }

        } catch (SQLException e) {
            throw new ResearchDataAccessException("Une erreur est survenue lors de la recherche des matchs");
        }

        return matchData;
    }

    @Override
    public ArrayList<MatchData> getMatchData(IdAccount idAccount) throws ResearchDataAccessException {
        return getMatchData(idAccount, 20);
    }

}
