package dataAccessPackage.rankDataAccess;

import exceptionPackage.IllegalAccountArgumentException;
import dataAccessPackage.SingletonConnection;
import exceptionPackage.rank.ReadRankException;
import modelPackage.accountModel.Rank;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RankDBAccess implements RankDataAccess{

    private Connection connection;

    public RankDBAccess(){
        this.connection = SingletonConnection.getInstance();
    }

    @Override
    public ArrayList<Rank> getAllRanks() throws ReadRankException{
        ArrayList<Rank> ranks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `rank`");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ranks.add(new Rank(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException|IllegalAccountArgumentException e) {
            throw new ReadRankException(e.getMessage());
        }
        return ranks;
    }
}
