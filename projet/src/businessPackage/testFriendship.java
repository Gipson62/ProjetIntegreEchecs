package businessPackage;

import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import modelPackage.accountModel.IdAccount;
import dataAccessPackage.friendshipDataAccess.*;
import exceptionPackage.friendship.*;
import modelPackage.Friendship;
import exceptionPackage.IllegalAccountArgumentException;
import modelPackage.accountModel.IdAccount;
import java.util.ArrayList;
public class testFriendship {
    public static void main(String[] args) {
        FriendshipManager friendshipManager = new FriendshipManager();
        System.out.println("Test FriendshipManager");
        Friendship friendship = null;
        try
        {
           friendship = new Friendship(18, 8);
        }catch (IllegalAccountArgumentException e){
            System.out.println("Test FriendshipManager failed"+ e.getMessage());
        }

        System.out.println("Test deleteFriendship");
        try {
            friendshipManager.deleteFriendship(friendship);
            System.out.println("Test deleteFriendship passed");
        } catch (Exception e) {
            System.out.println("Test deleteFriendship failed"+ e.getMessage());
        }


        System.out.println("Test addFriendship");
        try {
            friendshipManager.addFriendship(friendship);
            System.out.println("Test addFriendship passed");
        } catch (Exception e) {
            System.out.println("Test addFriendship failed"+ e.getMessage());
        }





        System.out.println("Test getFriendList");
        try {
            friendshipManager.getFriendList(new IdAccount( friendship.getIdAccount1()));
            System.out.println("Test getFriendList passed");
        } catch (Exception e) {
            System.out.println("Test getFriendList failed" + e.getMessage());
        }
    }
}
