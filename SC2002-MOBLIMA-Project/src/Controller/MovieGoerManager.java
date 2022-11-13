package Controller;

import Entity.CentralManagerEY;
import Entity.MovieGoerEY;
import Entity.UserEY;
import Enum.UserTypeEN;

import java.util.ArrayList;


/**
 * A MovieGoer Manager object 
 * 
 * <p>
 * A <code>MoviegoerManager</code> object used to process all 
 * MovieGoer related information
 * </p>
 */
public class MovieGoerManager extends Manager implements BaseManager {
    // managers

    private ArrayList<UserEY> masterUserList;

    /**
     * Constructor for MovieGoerManager
     */
    public MovieGoerManager() {

          //movieGoerIO=new mov
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setMasterLists() {
        CentralManagerEY centralMgr = this.getCentralManager();
        this.masterUserList = centralMgr.getMasterUsers();
    }

    /**
     * Method to get MovieGoer user by UserID from master user list
     * @param userID The userID
     * @return The MovieGoer
     */
    public MovieGoerEY getUserByID(String userID) {
        for ( UserEY user : this.masterUserList){
            if ( (user.getUserType().equals(UserTypeEN.MOVIEGOER)) && user.getUserID().equals(userID )) {
                return (MovieGoerEY) user;
            }
        }
        return null;      
    }
/**Method to get movie goer by username
* @param userName The user name
* @return the MovieGoerEY object
*/
    public MovieGoerEY getUserByName(String userName) { // gets user by specified userName, if not found return null
        for (UserEY user: this.masterUserList) {
            if (user.getUserName().equals(userName)) {
                return (MovieGoerEY) user;
            }
        }

        return null;
    }
	/**
     * Method to get all MovieGoers from master user list
     * @return All the MovieGoer user
     */
    public ArrayList<MovieGoerEY> getAllMovieGoers() {
        ArrayList<MovieGoerEY> movieGoerEYList = new ArrayList<MovieGoerEY>();
        for ( UserEY user : this.masterUserList){
            if ((user.getUserType().equals(UserTypeEN.MOVIEGOER))) {
                movieGoerEYList.add((MovieGoerEY) user);
            }
        }
        return movieGoerEYList;
    }
}
