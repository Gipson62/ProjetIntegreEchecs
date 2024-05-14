package dataAccessPackage.friendshipDataAccess;

import dataAccessPackage.SingletonConnection;
import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.IdAccount;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class FriendshipDBAccess implements FriendshipDataAccess{

    private Connection connection;

    public FriendshipDBAccess(){
        connection = SingletonConnection.getInstance();
    }


    public void addFriendship(Friendship friendship) throws AddFriendshipException{
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Friendship (Friend_1, Friend_2) VALUES (?, ?)");
            preparedStatement.setInt(1, friendship.getIdAccount1());
            preparedStatement.setInt(2, friendship.getIdAccount2());

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw new AddFriendshipException(e.getMessage());
        }
    }

    public void deleteFriendship(Friendship friendship) throws DeleteFriendshipException{
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Friendship WHERE Friend_1 = ? AND Friend_2 = ?");
            preparedStatement.setInt(1, friendship.getIdAccount1());
            preparedStatement.setInt(2, friendship.getIdAccount2());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            throw new DeleteFriendshipException(e.getMessage());
        }
    }

    public ArrayList<Friendship> getFriendList(IdAccount account) throws GetFriendListException{

        ArrayList<Friendship> friendList = new ArrayList<>();
        try{
            int idAccount = account.getIdAccount();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  *\n" +
                    "FROM (SELECT Friend_2 FROM Friendship WHERE Friend_1 = ? UNION SELECT Friend_1 FROM Friendship WHERE Friend_2 = ?) as friend");
            preparedStatement.setInt(1, idAccount);
            preparedStatement.setInt(2, idAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                try {
                    friendList.add(new Friendship(idAccount, resultSet.getInt("Friend_2")));
                } catch (IllegalAccountArgumentException e) {
                    e.printStackTrace();
                }
            }

        }catch(SQLException e){
            throw new GetFriendListException(e.getMessage());
        }
        return friendList;
    }

}
