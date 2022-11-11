import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import javafx.scene.shape.MoveTo;


public class MovieManager extends Manager implements BaseManager {

    private MovieTypeEN movieType;
    private MovieRatingEN movieRating;
    private ShowStatusEN showStatus;
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewEY> masterRatings;
    private MovieBoundary movieIO;
    
    private MovieManager movieMgr;
    private ReviewManager reviewMgr;
    public MovieManager (ReviewManager reviewMgr) {
       this.reviewMgr = reviewMgr;
       movieIO = new MovieBoundary(this, reviewMgr);

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
    public MovieEY getMovieByID(String movieID) {
        for(MovieEY m: masterMovies) {
            if(movieID.equals(m.getMovieID()))
                return m;
            else
                System.out.println("No movie exists with this ID!");
        }
        return null;
    }

    public void addMovie() {
        char insertUpdateFlag = 'I';
        String movieName = movieIO.setMovieName();
        String language  = movieIO.setLanguage(insertUpdateFlag);
        String movieType = movieIO.setMovieType(insertUpdateFlag);
        String movieRating = movieIO.setMovieRating(insertUpdateFlag);
        String showStatus = movieIO.setShowStatus(insertUpdateFlag);
        String synopsis = movieIO.setSynopsis(insertUpdateFlag);
        String director = movieIO.setDirector(insertUpdateFlag);
        ArrayList<String> cast = movieIO.setCast(insertUpdateFlag);
        ArrayList<String> ratingsID = new ArrayList<String>();

        // Check if movie already exits

        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                movieIO.printDuplicateMovie();
                return;
            }
        }
        MovieEY movie = new MovieEY("", movieName, language, MovieTypeEN.valueOf(movieType), MovieRatingEN.valueOf(movieRating), ShowStatusEN.valueOf(showStatus), synopsis, director, cast, ratingsID);
        movie.setReviews(masterRatings);
        masterMovies.add(movie);

        movieIO.printAddMovieSuccessMsg();
    }

    public ArrayList<MovieEY> getMasterList() {
        return masterMovies;
    }
    public void updateMovie() {
        char insertUpdateFlag = 'U';
        String movieName = movieIO.setMovieName();
        String language  = movieIO.setLanguage(insertUpdateFlag);
        String movieType = movieIO.setMovieType(insertUpdateFlag);
        String movieRating = movieIO.setMovieRating(insertUpdateFlag);
        String showStatus = movieIO.setShowStatus(insertUpdateFlag);
        String synopsis = movieIO.setSynopsis(insertUpdateFlag);
        String director = movieIO.setDirector(insertUpdateFlag);
        ArrayList<String> cast = movieIO.setCast(insertUpdateFlag);

        // Check if movie already exits

        MovieEY searchMovie = null;
        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            movieIO.printMissingMovie();
            return;
        }

        if (language != null ) { searchMovie.setLanguage(language); }
        if (movieType != null ) { searchMovie.setType(MovieTypeEN.valueOf(movieType)); }
        if (movieRating != null ) { searchMovie.setMovieRating(MovieRatingEN.valueOf(movieRating)); }
        if (showStatus != null ) { searchMovie.setShowStatus(ShowStatusEN.valueOf(showStatus)); }
        if (synopsis != null ) { searchMovie.setSynopsis(synopsis); }
        if (director != null ) { searchMovie.setDirector(director); }
        if (cast != null ) { searchMovie.setCast(cast); }

        movieIO.printUpdateMovieSuccessMsg();


    }
    public void updateMovieStatus() {
        char insertUpdateFlag = 'X';
        String movieName = movieIO.setMovieName();
        String showStatus = movieIO.setShowStatus(insertUpdateFlag);
        // Check if movie already exits

        MovieEY searchMovie = null;
        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            movieIO.printMissingMovie();
            return;
        }
        searchMovie.setShowStatus(ShowStatusEN.valueOf(showStatus));
        movieIO.printUpdateMovieSuccessMsg();

    }

    public void searchMovie() {
        char insertUpdateFlag = 'X';
        String movieName = movieIO.setMovieName();
        MovieEY searchMovie = null;
        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            movieIO.printMissingMovie();
            return;
        }
        System.out.println(searchMovie.toString());
    }

    public void listMovies() {
        Boolean currentlyShowing = movieIO.setCurrentlyShowing();
        movieIO.printMovieList(currentlyShowing);

    }

    public ArrayList<MovieEY> getCurrentMovies() {
        ArrayList<MovieEY> movies = new ArrayList<MovieEY>();
        for (MovieEY movie: masterMovies) {
            if (movie.getShowStatus() == ShowStatusEN.SHOWING | movie.getShowStatus() == ShowStatusEN.PREVIEW) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public String getMovieIDFromCurrentShowingIDX(Integer idx) {
        return this.getCurrentMovies().get(idx-1).getMovieID();
    }

    public void setCentralManager(CentralManagerEY CentralManager) {

    }

    public void getManager(BaseManager manager) {

    }

    @Override
    public BaseManager getManager(String managerName) {
        return null;
    }

    @Override
    public void getMasterList(ArrayList list) {

    }

    @Override
    public void setManagers() {

    }

    @Override
    public ArrayList getMasterList(String arrayName) {
        return null;
    }
}

