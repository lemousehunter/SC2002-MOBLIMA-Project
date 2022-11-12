import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MovieGoerManager extends Manager implements BaseManager {
    // managers

    private ArrayList<User> masterUserList;

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
        for ( User user : this.masterUserList){
            if ( (user.getUserType().equals(UserType.MOVIEGOER)) && user.getUserID().equals(userID )) {
                return (MovieGoerEY) user;
            }
        }
        return null;      
    }

    public ArrayList<MovieGoerEY> getAllMovieGoers() {
        ArrayList<MovieGoerEY> movieGoerEYList = new ArrayList<MovieGoerEY>();
        for ( User user : this.masterUserList){
            if ((user.getUserType().equals(UserType.MOVIEGOER))) {
                movieGoerEYList.add((MovieGoerEY) user);
            }
        }
        return movieGoerEYList;
    }

    public void process() {
    }

}
