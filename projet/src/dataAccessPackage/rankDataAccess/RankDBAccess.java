package dataAccessPackage.rankDataAccess;

import exceptionPackage.rank.ReadRankException;
import exceptionPackage.IllegalAccountArgumentException;
import dataAccessPackage.SingletonConnection;
import modelPackage.accountModel.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementation of the IRankDataAccess interface to interact with the database.
 */
public class RankDBAccess implements IRankDataAccess {

    /**
     * Constructor establishes a connection to the database using a singleton pattern.
     */
    public RankDBAccess() { }

    /**
     * Get all ranks from the database.
     * @return A list of all ranks from the database.
     * @throws ReadRankException If an SQL exception occurs or the account argument is illegal.
     */
    @Override
    public ArrayList<Rank> getAllRanks() throws ReadRankException {
        ArrayList<Rank> ranks = new ArrayList<>();
        try {
            PreparedStatement ps = SingletonConnection.getInstance().prepareStatement("SELECT * FROM `rank`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ranks.add(new Rank(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException | IllegalAccountArgumentException e) {
            throw new ReadRankException("Une erreur est survenue lors de la récupération des rangs.");
        }
        return ranks;
    }
}
