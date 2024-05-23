package dataAccessPackage.friendshipDataAccess;

import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import modelPackage.accountModel.IdAccount;

import java.util.ArrayList;

/**
 * Interface defining the data access operations for managing friendships.
 */
public interface FriendshipDataAccess {

     /**
      * Adds a new friendship to the data store.
      *
      * @param friendship The Friendship object representing the new friendship to be added.
      * @throws AddFriendshipException If the operation fails, for example due to a database error or a constraint violation.
      */
     public void addFriendship(Friendship friendship) throws AddFriendshipException;

     /**
      * Deletes an existing friendship from the data store.
      *
      * @param friendship The Friendship object representing the friendship to be deleted.
      * @throws DeleteFriendshipException If the deletion operation fails, for example due to a database error.
      */
     public void deleteFriendship(Friendship friendship) throws DeleteFriendshipException;

     /**
      * Retrieves a list of all friendships associated with a specific user account.
      *
      * @param account The IdAccount object identifying the user whose friend list is to be retrieved.
      * @return ArrayList<Friendship> A list of Friendship objects representing the user's friends.
      * @throws GetFriendListException If the retrieval operation fails, for example due to a database error.
      */
     public ArrayList<Friendship> getFriendList(IdAccount account) throws GetFriendListException;
}
