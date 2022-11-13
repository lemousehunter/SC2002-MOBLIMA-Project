package Controller;

import Entity.CentralManagerEY;
import Entity.MovieGoerEY;
import Entity.UserEY;
import Enum.UserTypeEN;

import java.util.ArrayList;

public class MovieGoerManager extends Manager implements BaseManager {
    // managers

    private ArrayList<UserEY> masterUserList;

    public MovieGoerManager() {

          //movieGoerIO=new mov
    }

    @Override
    public void setManagers() {
    }

    @Override
    public void setMasterLists() {
        CentralManagerEY centralMgr = this.getCentralManager();
        this.masterUserList = centralMgr.getMasterUsers();
    }

    public MovieGoerEY getUserByID(String userID) {
        for ( UserEY user : this.masterUserList){
            if ( (user.getUserType().equals(UserTypeEN.MOVIEGOER)) && user.getUserID().equals(userID )) {
                return (MovieGoerEY) user;
            }
        }
        return null;      
    }

    public MovieGoerEY getUserByName(String userName) { // gets user by specified userName, if not found return null
        for (UserEY user: this.masterUserList) {
            if (user.getUserName().equals(userName)) {
                return (MovieGoerEY) user;
            }
        }

        return null;
    }

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
