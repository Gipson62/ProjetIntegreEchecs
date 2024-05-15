package businessPackage;

import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import modelPackage.accountModel.IdAccount;
import dataAccessPackage.friendshipDataAccess.*;

import java.util.ArrayList;
public class FriendshipManager {

    FriendshipDataAccess dao;

    public FriendshipManager(){
        dao = new FriendshipDBAccess();

        }

    public void addFriendship(Friendship friendship) throws AddFriendshipException{
        dao.addFriendship(friendship);
    }

    public void deleteFriendship(Friendship friendship) throws DeleteFriendshipException{
        dao.deleteFriendship(friendship);
    }


    //demande
    public ArrayList<Friendship> getFriendList(IdAccount account) throws GetFriendListException{
        return dao.getFriendList(account);
    }
}
