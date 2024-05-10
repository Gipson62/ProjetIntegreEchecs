package dataAccessPackage.research;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.IllegalAccountArgumentException;
import exceptionPackage.research.*;
import modelPackage.research.*;
import exceptionPackage.research.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;


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
            System.out.println("piont 1");
            PreparedStatement preparedStatement = connection.prepareStatement("select *\n" +
                    "from match_outcomes\n" +
                    "where you =?\n" +
                    "and elo2 > ?\n" +
                    "and start_date between ? and ?\n" +
                    "order by start_date ;");

            System.out.println("piont 2");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, eloMin);
            System.out.println("piont 3");
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
}
