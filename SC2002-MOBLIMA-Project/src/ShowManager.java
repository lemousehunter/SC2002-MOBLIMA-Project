import java.util.ArrayList;
import java.util.Scanner;

public class ShowManager implements Manager {

    static Scanner sc = new Scanner(System.in);
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewEY> masterRatings;
        
    ScreenManager screenMgr;
    MovieManager movieMgr;
    public ShowManager(ScreenManager screenMgr, MovieManager movieMgr) {
        this.screenMgr = screenMgr;
        this.movieMgr = movieMgr;
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
    ArrayList<ReviewEY> masterRatings) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
  }

    public void addShowC(String showDate, String showTime, String movieName, String screenName, int emptySeats, int numofRows,
            int seatsPerRow) {

        // lookup from movies master list for matching movename and get movieID
        boolean movieFound = false;
        String movieID=null;
        MovieEY movie=null;
        for (int i = 0; i < masterMovies.size(); i++) {
            MovieEY movieIterator = masterMovies.get(i);
            if (movieIterator.getName().equals(movieName)) {
                movie=movieIterator;
                movieID = movieIterator.getMovieID();
                movieFound = true;
                break;
            }
        }
        if (!movieFound) {
            throw new showException("Movie " + movieName + "does not exist!");
        }

        // lookup from screens master list for matching screename and get screenI
        boolean screenFound = false;
        String screenID=null;
        ScreenEY screen=null;
        for (int i = 0; i < masterScreens.size(); i++) {
            ScreenEY screenIterator = masterScreens.get(i);
            if (screenIterator.getScreenName().equals(screenName)) {
                screen=screenIterator;
                screenID = screenIterator.getScreenID();
                screenFound = true;
                break;
            }
        }
        if (!screenFound) {
            throw new showException("screen " + screenName + "does not exist!");
        }

        // lookup shows to make sure that showdate and showtime is not already
        // configured with a show
        for (int i = 0; i < masterShows.size(); i++) {
            ShowEY show = masterShows.get(i);
            if (show.getShowDate().equals(showDate) && show.getShowTime().equals(showTime)
                    && show.getScreen().getScreenName().equals(screenName)) {
                throw new showException("Movie slot on " + screenName + " is already scheduled at " + showTime + " on "
                        + showDate + ". Unable to add a show on this date and time");
            }
        }

        ShowEY show = new ShowEY("", movieID, screenID, showDate, showTime, emptySeats, numofRows, seatsPerRow, this.movieMgr, this.screenMgr);
        masterShows.add(show);
    }
    // public ArrayList<String> getShowListByDate(String showDate) {
    //     ArrayList<String> showListbyDate = new ArrayList<String>();

    //     for (int i = 0; i < shows.size(); i++) {
    //         Show show = shows.get(i);
    //         if (show.getShowDate().equals(showDate)) {
    //             showListbyDate.add(show.getShowID());
    //         }
    //     }
    //     return showListbyDate;

    // }

    // public ArrayList<String> getShowListByTime(String showDate, String showTime) {
    //     ArrayList<String> showListbyTime = new ArrayList<String>();

    //     for (int i = 0; i < shows.size(); i++) {
    //         Show show = shows.get(i);
    //         if (show.getShowTime().equals(showTime)) {
    //             showListbyTime.add(show.getShowTime());
    //         }
    //     }
    //     return showListbyTime;
    // }

    // public Show getShowByID(String showID) {
    //     for (int i = 0; i < shows.size(); i++) {
    //         Show show = shows.get(i);
    //         if (show.getShowID().equals(showID)) {
    //             return show;
    //         }
    //     }
    //     return null;
    // }

    public void addShowB() {
        System.out.println("Enter Show Date: ");
        String date = sc.nextLine();
        System.out.println("Enter Show Time: ");
        String time = sc.next();
        System.out.println("Enter Movie Name: ");
        String movieName = sc.next();
        System.out.println("Enter Screen Name: ");
        String screenName = sc.next();
        System.out.println("Enter total seat capacity: ");
        int emptySeats = sc.nextInt();
        System.out.println("Enter number of rows: ");
        int numOfRows = sc.nextInt();
        System.out.println("Enter number of number of seats per row: ");
        int seatsPerRow = sc.nextInt();

        this.addShowC(date, time, movieName, screenName, emptySeats, numOfRows, seatsPerRow);
    }

    public ArrayList<ShowEY> viewShowbyMovieAndScreen(String movieName, String screenName) {
        String movieID = "";
        for (MovieEY movie : masterMovies) {
            if (movie.getName().equals(movieName)) {
                movieID = movie.getMovieID();
                break;
            }
        }
        if (movieID.equals("")) {
            throw new showException("No shows for Movie " + movieName);
        }
        String screenID = "";
        for (ScreenEY screen : masterScreens) {
            if (screen.getScreenName().equals(screenName)) {
                screenID = screen.getScreenID();
                break;
            }
        }
        if (screenID.equals("")) {
            throw new showException("No screens for the show " + screenName);
        }
        System.out.println("The shows for the Movie "+movieName+ " at Screen "+screenName+" are: ");
        ArrayList<String> printShows = new ArrayList();
        StringBuilder st = new StringBuilder();
        String prevShowDate="";
        ArrayList<ShowEY> showListForMovie = new ArrayList<ShowEY>();
        for (ShowEY show : masterShows) {

            if (show.getMovieID().equals(movieID) && show.getScreenID().equals(screenID)) {
                showListForMovie.add(show);
                if (!show.getShowDate().equals(prevShowDate)) {
                    printShows.add(st.toString());
                    printShows.add("Date: " + show.getShowDate().trim());
                    prevShowDate=show.getShowDate();

                    st = new StringBuilder();
                }
                st.append("   " + show.getShowTime().trim());
            }
        }
        if (st.length() != 0 ){
            printShows.add(st.toString());
        }
        for (String printline : printShows){
            System.out.println(printline);
        }
        return showListForMovie;
        
    }
    public ArrayList<ScreenEY> viewShowScreensByMovie(String movieName){
        String movieID = "";
        for (MovieEY movie : masterMovies) {
            if (movie.getName().equals(movieName)) {
                movieID = movie.getMovieID();
                break;
            }
        }
        if (movieID.equals("")) {
            throw new showException("No shows for the Movie " + movieName);
        }
        ArrayList<ScreenEY> movieScreens= new ArrayList<ScreenEY>();
        for (ShowEY show : masterShows) {

            if (show.getMovieID().equals(movieID) && !(movieScreens.contains(show.getScreen()))) {
               movieScreens.add(show.getScreen());
            }
        }
        if (movieScreens.isEmpty()){
            throw new showException("No screens for the Movie " + movieName);
        }
        System.out.println("\n "+ movieName+" is available in the following screens");
        for (ScreenEY screen : movieScreens){
            System.out.println(screen.getScreenName());
        }
        return movieScreens;
        
    }
    public ArrayList<MovieEY> viewMovieListing(ShowStatusEN showStatus){
        ArrayList<MovieEY> movieList= new ArrayList<MovieEY>();
        for (MovieEY movie : masterMovies){
            if (movie.getShowStatus().equals(showStatus)){
                movieList.add(movie);
            }
        }
        System.out.println("\n " +showStatus.toString()+ " Movies");
        for (MovieEY movie : movieList){
            System.out.println(movie.getName());
        }
        return movieList;

    }
    
  
}
