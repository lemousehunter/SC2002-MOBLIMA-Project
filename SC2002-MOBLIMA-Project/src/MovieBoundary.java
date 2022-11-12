import java.util.ArrayList;
import java.util.Arrays;

public class MovieBoundary extends Boundary implements BaseBoundary {
    // managers
    private MovieManager movieManager;
    private ReviewManager reviewManager;

    // boundaries
    private ReviewBoundary reviewBoundary;

    @Override
    public void setManagers() {
        this.reviewManager = this.getCentralManager().getReviewMgr();
        this.movieManager = this.getCentralManager().getMovieMgr();
    }

    @Override
    public void setBoundaries() {
        this.reviewBoundary = this.getCentralManager().getReviewBoundary();
    }

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

    public void printMovieList() {
        boolean currentlyShowing = this.getCurrentlyShowing();
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

        for (String line: this.movieManager.getAllMovies()) {
            this.println(line);
        }
    }

    public String getMovieName() {
        return this.getInputLine("Enter Movie Name: ");
    }

    public String getMoviePartName() {
        return this.getInputLine("Enter Movie Name (Partial starting with or Full Name) : ");
    }

    public String getLanguage(char insertUpdateFlag) { // returns language if insert or if update and y else return null
        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Language ?  (Y | N) ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        return this.getInputLine("Enter Movie Language: ");
    }

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

    public String getSynopsis(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Movie Synopsis  ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }

        }

        return this.getInputLine("Enter Movie Synopsis: ");
    }

    public String getDirector(char insertUpdateFlag) {

        if (insertUpdateFlag == 'U') {
            String choice = this.getInputLine("Do you want to update Director Name  ?  (Y | N) : ").toUpperCase();
            if (!choice.equals("Y")) {
                return null;
            }
        }

        return this.getInputLine("Enter Movie Director Name: ");
    }

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

    public Boolean getCurrentlyShowing() {
        String currentlyShowing = this.getInputLine("Do you want to see Current Showing Movies ONLY : (Y | N)  : ");
        return currentlyShowing.equalsIgnoreCase("Y");
    }

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
                    this.printMovieList();
                    break;
                case 5:
                     movieName = this.getMoviePartName();
                     movieName = this.getScanner().nextLine();
                     this.searchMovie(movieName);

                    break;
                case 6:
                    break;
            }
        }
    }

    private void searchMovie(String movieName) {
        String printString = this.movieManager.searchMovie(movieName);
        if (printString == null) {
            this.println("\n No movies found with  name matching " + movieName);
            return;
        }
        this.println(printString);
    }

}
