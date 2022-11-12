import java.util.ArrayList;
import java.util.Scanner;

public class ShowManager extends Manager implements BaseManager {
    // arrays
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;

    // Managers
    private ScreenManager screenMgr;
    private MovieManager movieMgr;

    public ShowManager() {

    }

    @Override
    public void setManagers() {
        this.screenMgr = this.getCentralManager().getScreenMgr();
        this.movieMgr = this.getCentralManager().getMovieMgr();
    }

    @Override
    public void setMasterLists() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.masterMovies = centralManager.getMasterMovies();
        this.masterShows = centralManager.getMasterShows();
        this.masterScreens = centralManager.getMasterScreens();
    }

    public void addShowC(String showDate, String showTime, String movieName, String screenName, int emptySeats, int numofRows,
                         int seatsPerRow) {

        // lookup from movies master list for matching movename and get movieID
        boolean movieFound = false;
        String movieID = null;
        MovieEY movie = null;
        for (MovieEY movieIterator : this.masterMovies) {
            if (movieIterator.getName().equals(movieName)) {
                movie = movieIterator;
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
        String screenID = null;
        ScreenEY screen = null;
        for (ScreenEY screenIterator : this.masterScreens) {
            if (screenIterator.getScreenName().equals(screenName)) {
                screen = screenIterator;
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
        for (ShowEY show : this.masterShows) {
            if (show.getShowDate().equals(showDate) && show.getShowTime().equals(showTime)
                    && show.getScreen().getScreenName().equals(screenName)) {
                throw new showException("Movie slot on " + screenName + " is already scheduled at " + showTime + " on "
                        + showDate + ". Unable to add a show on this date and time");
            }
        }

        ShowEY show = new ShowEY("", movieID, screenID, showDate, showTime, emptySeats, numofRows, seatsPerRow, this.movieMgr, this.screenMgr);
        this.masterShows.add(show);
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

    public ArrayList<ShowEY> viewShowbyMovieAndScreen(String movieName, String screenName) {
        String movieID = "";
        for (MovieEY movie : this.masterMovies) {
            if (movie.getName().equals(movieName)) {
                movieID = movie.getMovieID();
                break;
            }
        }
        if (movieID.equals("")) {
            throw new showException("No shows for Movie " + movieName);
        }
        String screenID = "";
        for (ScreenEY screen : this.masterScreens) {
            if (screen.getScreenName().equals(screenName)) {
                screenID = screen.getScreenID();
                break;
            }
        }
        if (screenID.equals("")) {
            throw new showException("No screens for the show " + screenName);
        }
        System.out.println("The shows for the Movie " + movieName + " at Screen " + screenName + " are: ");
        ArrayList<String> printShows = new ArrayList();
        StringBuilder st = new StringBuilder();
        String prevShowDate = "";
        ArrayList<ShowEY> showListForMovie = new ArrayList<ShowEY>();
        for (ShowEY show : this.masterShows) {

            if (show.getMovieID().equals(movieID) && show.getScreenID().equals(screenID)) {
                showListForMovie.add(show);
                if (!show.getShowDate().equals(prevShowDate)) {
                    printShows.add(st.toString());
                    printShows.add("Date: " + show.getShowDate().trim());
                    prevShowDate = show.getShowDate();

                    st = new StringBuilder();
                }
                st.append("   " + show.getShowTime().trim());
            }
        }
        if (st.length() != 0) {
            printShows.add(st.toString());
        }
        for (String printline : printShows) {
            System.out.println(printline);
        }
        return showListForMovie;

    }

    public ArrayList<ScreenEY> viewShowScreensByMovie(String movieName) {
        String movieID = "";
        for (MovieEY movie : this.masterMovies) {
            if (movie.getName().equals(movieName)) {
                movieID = movie.getMovieID();
                break;
            }
        }
        if (movieID.equals("")) {
            throw new showException("No shows for the Movie " + movieName);
        }
        ArrayList<ScreenEY> movieScreens = new ArrayList<ScreenEY>();
        for (ShowEY show : this.masterShows) {

            if (show.getMovieID().equals(movieID) && !(movieScreens.contains(show.getScreen()))) {
                movieScreens.add(show.getScreen());
            }
        }
        if (movieScreens.isEmpty()) {
            throw new showException("No screens for the Movie " + movieName);
        }
        System.out.println("\n " + movieName + " is available in the following screens");
        for (ScreenEY screen : movieScreens) {
            System.out.println(screen.getScreenName());
        }
        return movieScreens;

    }

    public ArrayList<MovieEY> viewMovieListing(ShowStatusEN showStatus) {
        ArrayList<MovieEY> movieList = new ArrayList<MovieEY>();
        for (MovieEY movie : this.masterMovies) {
            if (movie.getShowStatus().equals(showStatus)) {
                movieList.add(movie);
            }
        }
        System.out.println("\n " + showStatus.toString() + " Movies");
        for (MovieEY movie : movieList) {
            System.out.println(movie.getName());
        }
        return movieList;

    }
}
