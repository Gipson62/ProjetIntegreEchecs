package businessPackage;

import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import modelPackage.accountModel.IdAccount;
import dataAccessPackage.friendshipDataAccess.*;

import java.util.ArrayList;

/**
 * Manages operations related to friendships between users.
 * Facilitating operations related to adding, deleting, and retrieving friendships.
 */
public class FriendshipManager {

    private FriendshipDataAccess dao;

    /**
     * An instance of FriendshipDBAccess is created and assigned to dao.
     */
    public FriendshipManager() {
        dao = new FriendshipDBAccess();
    }

    /**
     * Adds a new friendship connection between two users.
     *
     * @param friendship The Friendship object representing the connection to be added.
     * @throws AddFriendshipException If there is an error while adding the friendship.
     */
    public void addFriendship(Friendship friendship) throws AddFriendshipException {
        dao.addFriendship(friendship);
    }

    /**
     * Deletes an existing friendship connection between two users.
     *
     * @param friendship The Friendship object representing the connection to be deleted.
     * @throws DeleteFriendshipException If there is an error while deleting the friendship.
     */
    public void deleteFriendship(Friendship friendship) throws DeleteFriendshipException {
        dao.deleteFriendship(friendship);
    }

    /**
     * Retrieves the list of all friends associated with a specific account.
     *
     * @param account The IdAccount object identifying the user whose friends list is to be retrieved.
     * @return ArrayList<Friendship> A list of Friendship objects representing the friends of the user.
     * @throws GetFriendListException If there is an error while retrieving the friend list.
     */
    public ArrayList<Friendship> getFriendList(IdAccount account) throws GetFriendListException {
        return dao.getFriendList(account);
    }
}
