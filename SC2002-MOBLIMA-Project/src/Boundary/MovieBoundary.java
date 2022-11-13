package Boundary;

import Controller.MovieManager;
import Controller.ReviewManager;
import Controller.ShowManager;
import Entity.MovieEY;
import Entity.ReviewEY;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * A Movie Boundary Object
 * 
 * <p>
 * A <code>MovieBoundary</code> object used 
 *  process all Movie input and output
 * </p>
 * 
 */
public class MovieBoundary extends Boundary implements BaseBoundary {
    // managers
    /**
     * A private MovieManager object 
     */
    private MovieManager movieManager;
    /**
     * A private ReviewManager object
     */
    private ReviewManager reviewManager;
    /**
     * A private ShowManager  object
     */
    private ShowManager showMgr;

    // boundaries
    /**
     * A private ReviewBoundary object
     */
    private ReviewBoundary reviewBoundary;

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.reviewManager = this.getCentralManager().getReviewMgr();
        this.movieManager = this.getCentralManager().getMovieMgr();
        this.showMgr = this. getCentralManager().getShowMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {
        this.reviewBoundary = this.getCentralManager().getReviewBoundary();
    }

    /**
     * Method to view movie details
     * @param movieID The movieID
     */
    public void viewMovieDetails(String movieID) {
        MovieEY movie = this.movieManager.getMovieByID(movieID);
        this.println("Movie Name: " + movie.getName());
        this.println("Movie Status: " + movie.getShowStatus());
        this.println("Movie Type: " + movie.getMovieType().toString());
        this.println("Movie Rating: " + movie.getMovieRating());
        this.println("Movie Language: " + movie.getLanguage());
        this.println("Synopsis: " + movie.getSynopsis());
        this.println("Director: " + movie.getDirector());
        this.println("Cast: " + movie.getCast());
        this.println("Viewer Review and Ratings:");

        for (ReviewEY vr : this.reviewManager.getMovieRatings(movie)) {
            if (vr.getMovieId().equals(movieID)) {
                this.println("\nReview: " + vr.getReview());
                this.println("Rating: " + vr.getRating());
            }
        }

        this.reviewBoundary.AvgRatingPrint(movieID);
    }

	/**
     * Method to print movie list
     */
    public void printMovieList(boolean askCurrent) { // if askCurrent, then ask if user wants to view current movies only, else defaults to true
        boolean currentlyShowing;
        if (askCurrent) {
            currentlyShowing = this.getCurrentlyShowing();
        }
        else {
            currentlyShowing = true;
        }
        int count = 1;
        this.println("\nList of Movies:\n");
        ArrayList<String> lines = new ArrayList<String>();

        if (currentlyShowing) {
            lines = this.movieManager.getCurrentMoviesLines();
            this.println("Showing Currently Showing movies");
        } else {
            lines = this.movieManager.getAllMovies();
            this.println("Showing ALL movies in movie list.");
        }

        for (String line: lines) {
            this.println(line);
        }
    }

    /**
     * Method to get movie name from user
     * @return The movie name
     */
    public String getMovieName() {
        return this.getInputLine("Enter Movie Name: ");
    }

    /**
     * method to get partial or full movie name from user
     * @return The movie name
     */
    public String getMoviePartName() {
        return this.getInputLine("Enter Movie Name (Partial starting with or Full Name) : ");
    }

    /**
     * Method to update movie language from user
     * @param insertUpdateFlag The update flag indicator
     * @return The movie language
     */
    public String getLanguage(char insertUpdateFlag) { // returns language if insert or if update and y else return null
        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Language ?  (Y | N) ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        return this.getInputLine("Enter Movie Language: ");
    }

    /**
     * Method get movie type from user
     * @param insertUpdateFlag update flag indicator
     * @return The movie type (BLOCKBUSTER,THREEDIMENSION,DOCUMENTARY)
     */
    public String getMovieType(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Type Classification ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        String movieType;
        while (true) {
            movieType = this.getInputLine("Enter Movie Type Classification (B -> BLOCKBUSTER, 3D -> THREEDIMENSION, D -> DOCUMENTARY) : ").toUpperCase();
            movieType = switch (movieType) {
                case "B" -> "BLOCKBUSTER";
                case "3D" -> "THREEDIMENSION";
                case "D" -> "DOCUMENTARY";
                default -> null;
            };
            if (movieType == null) {
                this.println("You have entered Invalid Movie Type Classification !!");
            }
            else {
                break;
            }
        }
        return movieType;
    }

    /**
     * Method to get movie restriction rating from user
     * @param insertUpdateFlag An update flag indicator
     * @return The movie restriction rating
     */
    public String getMovieRating(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Rating  ?  (Y | N) :  ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        String movieRating;
        while (true) {
            movieRating = this.getInputLine("Enter Movie Rating (G | PG | PG13 | R | X) : ").toUpperCase();
            if (movieRating.equals("G") |
                movieRating.equals("PG") |
                movieRating.equals("PG13") |
                movieRating.equals("R") |
                movieRating.equals("X")) {
                break;
            }
            this.println("You have entered Invalid Movie Rating Classification !!");
        }
        return movieRating;
    }

    /**
     * Method to get show status from user
     * @param insertUpdateFlag An update flag indicator
     * @return The movie showing status
     */
    public String getShowStatus(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Show Status  ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        String showStatus;
        while (true) {
            showStatus = this.getInputLine("Enter Show Status (C -> COMINGSOON, P -> PREVIEW, S -> SHOWING, E -> ENDOFSHOWING) : ").toUpperCase();

            showStatus = switch (showStatus) {
                case "C" -> "COMINGSOON";
                case "P" -> "PREVIEW";
                case "S" -> "SHOWING";
                case "E" -> "ENDOFSHOWING";
                default -> null;
            };
            if (showStatus == null) {
                this.println("You have entered Invalid Show Status Classification !!");
            }
            else {
                break;
            }
        }
        return showStatus;
    }

    /**
     * Method to get the movie synopsis from the user
     * @param insertUpdateFlag An update flag indicator
     * @return The movie synopsis
     */
    public String getSynopsis(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Synopsis  ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }

        }

        return this.getInputLine("Enter Movie Synopsis: ");
    }

    /**
     * Method to get director from the user
     * @param insertUpdateFlag An update flag indicator
     * @return The movie director
     */
    public String getDirector(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Director Name  ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        return this.getInputLine("Enter Movie Director Name: ");
    }

    /**
     * Method to get cast details from user
     * @param insertUpdateFlag An update flag indicator
     * @return The cast of the movie
     */
    public ArrayList<String> getCast(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie cast  ?  (Y | N) :  ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }
        String castString = this.getInputLine("Enter Movie cast (names separated by comma): ");
        return new ArrayList<String>(Arrays.asList(castString.split(",")));
    }

    /**
     * Method to implement flag to view currently showing movies only 
     * @return True if "y" else is False
     */
    public Boolean getCurrentlyShowing() {
        String currentlyShowing = this.getInputLine("Do you want to see Current Showing Movies ONLY : (Y | N)  : ");
        return currentlyShowing.equalsIgnoreCase("Y");
    }

    /**
     * Method to display user menu and get user selection
     * @return The user selection
     */
    public int getMovieMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
            "\n========================= Welcome to Staff App =========================\n" +
            "1.  Add Movie                                              \n" +
            "2.  Update Movie Details                                              \n" +
            "3.  Update Movie Show Status                                             \n" +
            "4.  List all Movies                                              \n" +
            "5.  Search Movies                                              \n" +
            "6.  Return to Staff Menu                                              \n" +
            "========================================================================\n" +
            "Enter choice: "
        );
        while (choice == -1) {
            choice = this.getInputInt("Please enter an integer value. \n");
        }

        return choice;
    }

    /**
     * Method to get movie update/add details details
     * @param insertUpdateFlag An update flag indicator
     */
    public void getDetails(char insertUpdateFlag) {
        String movieName = this.getMovieName();
        movieName = this.getScanner().nextLine();
        String language = this.getLanguage(insertUpdateFlag);
        String movieType = this.getMovieType(insertUpdateFlag);
        String showStatus = this.getShowStatus(insertUpdateFlag);
        String synopsis = this.getSynopsis(insertUpdateFlag);
        String movieRating = this.getMovieRating(insertUpdateFlag);
        String director = this.getDirector(insertUpdateFlag);
        ArrayList<String> cast = this.getCast(insertUpdateFlag);
        boolean success;
        if (insertUpdateFlag == 'I') {
            success = this.movieManager.addMovie(movieName, language, movieType, showStatus, synopsis, movieRating, director, cast);
        }
        else {
            success = this.movieManager.updateMovie(movieName, language, movieType, movieRating, showStatus, synopsis, director, cast);
        }
        if (success) {
            if (insertUpdateFlag == 'U') {
                this.println("\nSuccessfully updated " + movieName);
            }
            else {
                this.println("\nSuccessfully insert " + movieName);
            }
        }
        else {
            if (insertUpdateFlag == 'U') {
                this.println("\nError : Failed to update " + movieName + " as it does not exist");
            }
            else {
                this.println("\nError : Failed to insert " + movieName + " as it already exists.");
            }
        }
    }
	/**
	* Method to list all shows
	*
	*/
    public void listAllShows() {
        String movieName = this.getMovieName();
        MovieEY movie = this.movieManager.searchMovie(movieName);
        for ( String printLine : this.showMgr.getShowsByMovie(movie) )
        {
            this.println(printLine);

        }
    }
	/**Method to search for a movie and print it*/
    public void searchMovie() {
        String movieName = this.getMoviePartName();
        MovieEY movie = this.movieManager.searchMovie(movieName);
        if (movie == null) {
            this.println("\n No movies found with name matching '" + movieName + "'");
            return;
        }
        this.println(movie.toString());
    }
/**
     * Method to perform movie movie menu operations
     */
    public void movieOperations() {
        int movieChoice = 0;
        while (movieChoice != 6) {
            movieChoice = this.getMovieMenuChoice();
            if (movieChoice < 0 | movieChoice > 6) {
                this.println("Enter choice betwen 1-6 values only \n");
                continue;
            }
            switch (movieChoice) {
                case 1:
                    this.getDetails('I');
                    break;
                case 2:
                    this.getDetails('U');
                    break;
                case 3:
                    String movieName = this.getMovieName();
                    movieName = this.getScanner().nextLine();
                    String showStatus = this.getShowStatus('U');
                    boolean success = this.movieManager.updateMovieStatus(movieName, showStatus);
                    if (success) {
                        this.println("\nSuccessfully updated " + movieName + "showing status to " + showStatus);
                    }
                    else {
                        this.println("\nError : Failed to update " + movieName + " as it was not found in movie list.");
                    }
                    break;
                case 4:
                    this.printMovieList(true);
                    break;
                case 5:
                    this.searchMovie();

                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
    }

}
