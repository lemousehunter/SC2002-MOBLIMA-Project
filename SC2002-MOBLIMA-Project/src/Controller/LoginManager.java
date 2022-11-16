package Controller;

import Entity.StaffEY;
import Entity.UserEY;

import java.util.ArrayList;
/**
 * An LoginManager Object
 * 
 * <p>
 * An <code>LoginManager</code> object used 
 *  process handle the logging in of the user
 * </p>
 * 
 */
public class LoginManager extends Manager implements BaseManager {
    /**Array of all users */
    ArrayList<UserEY> masterUsers;
    /**An object of class UserManager */
    UserManager userManager;

    /**
     * Method to set a new user
     */
    @Override
    public void setManagers() {
        this.userManager = this.getCentralManager().getUserMgr();
    }

    /**
     * Method to set a new list of all users
     */
    @Override
    public void setMasterLists() {
        this.masterUsers = this.getCentralManager().getMasterUsers();
    }

    /**
     * Method to let a movie goer login to the MOBLIMA Application
     * @param username The given username
     * @return UserEY object
     */
    public UserEY loginUser(String username) {
        try {
            return this.userManager.getUserByUserName(username);
        } catch (ClassCastException e) {
            return null;
        }
    }

    
    /**
     * Method to let staff login to the MOBLIMA Application
     * @param username The username of Staff
     * @param password The password of that particular staff member
     * @return An object of StaffEY
     */
    public StaffEY loginStaff(String username, String password) {
        try {
            StaffEY staff = (StaffEY) this.userManager.getUserByUserName(username);
            if (staff != null) {
                if (staff.getPassword().equals(password)) {
                    return staff;
                }
            }
            return null;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
