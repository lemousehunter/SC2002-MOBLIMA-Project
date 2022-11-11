import java.util.ArrayList;

public class MovieGoerManager implements Manager {
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewEY> masterRatings;
    private MovieManager movieManager;
    private ShowManager showManager;
    private BookingManager bookingManager;
  
    public MovieGoerManager(){}
    public MovieGoerManager(MovieManager movieMgr, ShowManager showManager, BookingManager bookingMgr,
        ReviewManager reviewManager) {
          this.movieManager = movieMgr;
          this.showManager = showManager;
          this.bookingManager=bookingMgr;
          //movieGoerIO=new mov
    }
    
    @Override
  public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<CineplexEY> masterCineplexes,
    ArrayList<ScreenEY> masterScreens,
    ArrayList<BookingEY> masterBookings,
    ArrayList<ShowEY> masterShows,
    ArrayList<MovieEY> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ReviewEY> masterRatings
  ) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;

  }
 
    public MovieGoerEY getUserByID(String userID) {
        for ( User user : masterUserList){
            if ( (user.getUserType().equals(UserType.MOVIEGOER)) && user.getUserID().equals(userID )) {
                return (MovieGoerEY) user;
            }
        }
        return null;      
    }

    public void process() {
    }
}
