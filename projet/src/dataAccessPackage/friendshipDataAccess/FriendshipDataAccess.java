package dataAccessPackage.friendshipDataAccess;

import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import modelPackage.accountModel.IdAccount;

import java.util.ArrayList;

public interface FriendshipDataAccess {
     public void addFriendship(Friendship friendship) throws AddFriendshipException;
     public void deleteFriendship(Friendship friendship) throws DeleteFriendshipException;
     public ArrayList<Friendship> getFriendList(IdAccount account) throws GetFriendListException;

}
