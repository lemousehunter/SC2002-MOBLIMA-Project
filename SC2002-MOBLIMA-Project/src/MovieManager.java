import java.util.ArrayList;


public class MovieManager extends Manager implements BaseManager {

    // arrays
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<ReviewEY> masterRatings;


    public MovieManager () {

    }

    @Override
    public void setManagers() {

    }

    @Override
    public void setMasterLists() {
        this.masterMovies = this.getCentralManager().getMasterMovies();
        this.masterRatings = this.getCentralManager().getMasterRatings();
    }

    public MovieEY getMovieByID(String movieID) { // returns movie object associated with specified movieID, else return null
        for(MovieEY m: masterMovies) {
            if(movieID.equals(m.getMovieID()))
                return m;
        }
        return null;
    }


    public Boolean addMovie(String movieName, String language, String movieType, String showStatus, String synopsis, String movieRating, String director, ArrayList<String> cast) {
        // Check if movie already exits

        for (MovieEY movie: this.masterMovies) {
            if (movie.getName().equals(movieName)) {
                return false; // duplicate found
            }
        }
        ArrayList<String> ratingsID = new ArrayList<String>();
        MovieEY movie = new MovieEY("", movieName, language, MovieTypeEN.valueOf(movieType), MovieRatingEN.valueOf(movieRating), ShowStatusEN.valueOf(showStatus), synopsis, director, cast, ratingsID);
        movie.setReviews(this.masterRatings);
        this.masterMovies.add(movie);
        return true;
    }

    public Boolean updateMovie(String movieName, String language, String movieType, String movieRating, String showStatus, String synopsis, String director, ArrayList<String> cast) {
        // Check if movie already exits

        MovieEY movie = null;
        for (MovieEY m : masterMovies)
        {
            if (m.getName().equalsIgnoreCase(movieName)) {
                movie = m;
            }
        }
        if (movie == null){
            return false; // search error
        }

        if (language != null ) { movie.setLanguage(language); }
        if (movieType != null ) { movie.setType(MovieTypeEN.valueOf(movieType)); }
        if (movieRating != null ) { movie.setMovieRating(MovieRatingEN.valueOf(movieRating)); }
        if (showStatus != null ) { movie.setShowStatus(ShowStatusEN.valueOf(showStatus)); }
        if (synopsis != null ) { movie.setSynopsis(synopsis); }
        if (director != null ) { movie.setDirector(director); }
        if (cast != null ) { movie.setCast(cast); }

        return true; // success
    }
    public boolean updateMovieStatus(String movieName, String showStatus) { // returns false if no movie found, else returns true after updating
        // Check if movie already exits
        MovieEY searchMovie = null;
        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            return false;
        }
        searchMovie.setShowStatus(ShowStatusEN.valueOf(showStatus));
        return true;
    }

    public String searchMovie(String movieName) {
        MovieEY searchMovie = null;
        for (MovieEY movie : masterMovies)
        {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            return null;
        }
        return searchMovie.toString();
    }

    public ArrayList<String> getCurrentMoviesLines() {
        int count = 0;
        ArrayList<String> lines = new ArrayList<String>();
        for (MovieEY movie: this.masterMovies) {
            if (movie.getShowStatus() == ShowStatusEN.SHOWING | movie.getShowStatus() == ShowStatusEN.PREVIEW) {
                lines.add(count + ": " + movie.getName() + " - " + movie.getShowStatus());
                count++;
            }
        }
        return lines;
    }

    public ArrayList<MovieEY> getCurrentMovies() {
        ArrayList<MovieEY> movies = new ArrayList<MovieEY>();
        for (MovieEY movie: this.masterMovies) {
            if (movie.getShowStatus() == ShowStatusEN.SHOWING | movie.getShowStatus() == ShowStatusEN.PREVIEW) {
                movies.add(movie);
            }
        }

        return movies;
    }

    public ArrayList<String> getAllMovies() {
        int count = 1;
        ArrayList<String> lines = new ArrayList<String>();
        for (MovieEY m : this.masterMovies) {
            lines.add(count + ": " + m.getName() + " - " + m.getShowStatus());
            count++;
        }
        return lines;
    }

    public String getMovieIDFromCurrentShowingIDX(Integer idx) {
        return this.getCurrentMovies().get(idx-1).getMovieID();
    }
}

