package Controller;

import Entity.MovieEY;
import Entity.ReviewEY;
import Enum.ShowStatusEN;
import Enum.MovieTypeEN;
import Enum.MovieRatingEN;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A MovieManager object
 * <p>
 * A <code>MovieManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of Movie Class
 * </p>
 */
public class MovieManager extends Manager implements BaseManager {

    // managers
    private IoManager ioManager;

    // arrays
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<ReviewEY> masterRatings;


    /**
     * Constructor for movie manager
     */
    public MovieManager () {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }
    
    /**
     *{@inheritDoc}
     */
    @Override
    public void setMasterLists() {
        this.masterMovies = this.getCentralManager().getMasterMovies();
        this.masterRatings= this.getCentralManager().getMasterRatings();             
    }

    /**
     * Method to get movie object by movieID
     * @param movieID The movieID
     * @return The Movie object
     */
    public MovieEY getMovieByID(String movieID) { // returns movie object associated with specified movieID, else return null
        for(MovieEY m: this.masterMovies) {
            if(movieID.equals(m.getMovieID()))
                return m;
        }
        return null;
    }


    /**
     * Method to add movie
     * @param movieName The movie name
     * @param language The movie language
     * @param movieType The movie type
     * @param showStatus The showing status of the movie
     * @param synopsis The synopsis of the movie
     * @param movieRating The restriction type
     * @param director The director of the movie
     * @param cast The cast of the movie
     * @return True if successfully added, False if movie already exists
     */
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

    /**
     * Method to update movie attributes
     * @param movieName The movie name
     * @param language The movie language
     * @param movieType The movie type
     * @param showStatus The showing status of the movie
     * @param synopsis The synopsis of the movie
     * @param movieRating The restriction type
     * @param director The director of the movie
     * @param cast The cast of the movie
     * @return True if successfully update, False if search error
     */
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
    /**
     * Method to update movie status
     * @param movieName The movie name
     * @param showStatus The show status (COMINGSOON,PREVIEW,SHOWING,ENDOFSHOWING)
     * @return True if successfully updated the showing status, False if search error
     */
    public boolean updateMovieStatus(String movieName, String showStatus) { // returns false if no movie found, else returns true after updating
        // Check if movie already exits
        MovieEY searchMovie = null;
        for (MovieEY movie : this.masterMovies)
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

    /**
     * Method to search for movie object based on name
     * @param movieName The movie name
     * @return The movie object
     */
    public MovieEY searchMovie(String movieName) {
        MovieEY searchMovie = null;
        for (MovieEY movie : this.masterMovies)
        {
            if (movie.getName().toLowerCase().contains(movieName.toLowerCase())) {
                searchMovie = movie;
            }
        }
        if (searchMovie == null){
            return null;
        }
        return searchMovie;
    }

    /**
     * Method to get list of movies that have SHOWING/PREVIEW status
     * @return The list of details
     */
    public ArrayList<String> getCurrentMoviesLines() {
        int count = 1;
        ArrayList<String> lines = new ArrayList<String>();
        for (MovieEY movie: this.masterMovies) {
            if (movie.getShowStatus().toString().equals(ShowStatusEN.SHOWING.toString()) || 
                movie.getShowStatus().toString().equals(ShowStatusEN.PREVIEW.toString())) {
                lines.add(count + ": " + movie.getName() + " - " + movie.getShowStatus());
                count++;
            }
        }
        return lines;
    }

    /**
     * Method to get current movies that have SHOWING/PREVIEW status
     * @return The movies
     */
    public ArrayList<MovieEY> getCurrentMovies() {
        ArrayList<MovieEY> movies = new ArrayList<MovieEY>();
        for (MovieEY movie: this.masterMovies) {
            if (movie.getShowStatus().toString().equals(ShowStatusEN.SHOWING.toString()) || 
                movie.getShowStatus().toString().equals(ShowStatusEN.PREVIEW.toString())) {
                movies.add(movie);
            }
        }

        return movies;
    }

    /**
     * Method to get all movies 
     * @return The list of all movies with movie name and showing status
     */
    public ArrayList<String> getAllMovies() {
        int count = 1;
        ArrayList<String> lines = new ArrayList<String>();
        for (MovieEY m : this.masterMovies) {
            lines.add(count + ": " + m.getName() + " - " + m.getShowStatus());
            count++;
        }
        return lines;
    }

    /**
     * Method to get movieID from movies that are SHOWING/PREVIEW
     * @param idx the index of the movie
     * @return The movieID
     */
    public String getMovieIDFromCurrentShowingIDX(Integer idx) {
        return this.getCurrentMovies().get(idx-1).getMovieID();
    }
	/**
     * Method to get movieID from all Current Showing or Preview movies
     * @param idx the index of the movie
     * @return The movieID
     */
    public String getMovieIDFromAllShowingIDX(Integer idx) {
        
        if (masterMovies.get(idx-1).getShowStatus().toString().equals(ShowStatusEN.ENDOFSHOWING.toString()) ||
            masterMovies.get(idx-1).getShowStatus().toString().equals(ShowStatusEN.COMINGSOON.toString()))
            {
                return "N/A";
            }
        return (masterMovies.get(idx-1).getMovieID());
    }

    /**
     * Method to get movie object by movie name
     * @param movieName The movie name
     * @return movie object
     */
    public MovieEY getMovieByName(String movieName) {
        if (movieName.isEmpty()) { return null;}
        for (MovieEY movie: this.masterMovies) {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                return movie;
            }
        }
        return null;    
    }

    /**
     * Method to get movie name from movieID
     * @param movieID The movieID
     * @return The movie name
     */
    public String ID2Name(String movieID) {
        return this.getMovieByID(movieID).getName();
    }

    /**
     * Method to get movieID from movie name
     * @param movieName The movie name
     * @return The movieID
     */
    public String Name2ID(String movieName) {
        return this.getMovieByName(movieName).getMovieID();
    }

    /**
     * Method to read movie objects to text file
     * @throws IOException If there's read error
     */
    public void primeMovie() throws IOException {
        String moviesEPARATOR = "|";
        String castSEPERATOR = "~";
        String ratingSEPERATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Movies.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of Movie objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, moviesEPARATOR);// pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String movieID = star.nextToken().trim(); // first token
            String movieName = star.nextToken().trim(); // first token
            String movieLanguage = star.nextToken().trim(); // second token
            String movieType = star.nextToken().trim(); // third token
            String movieRating = star.nextToken().trim(); // fourth token
            String showstatus = star.nextToken().trim(); // fifth token
            String synopsis = star.nextToken().trim(); // sixth token
            String director = star.nextToken().trim(); // seventh token
            String cast = star.nextToken().trim(); // eighth token
            ArrayList<String> castList = new ArrayList<String>();
            StringTokenizer castToken = new StringTokenizer(cast, castSEPERATOR);
            while (castToken.hasMoreTokens()) {
                castList.add(castToken.nextToken().trim());
            }
            String rating = star.nextToken().trim();
            ArrayList<String> ratings = new ArrayList<String>();
            StringTokenizer ratingToken = new StringTokenizer(rating, ratingSEPERATOR);
            while (ratingToken.hasMoreTokens()) {
                ratings.add(ratingToken.nextToken().trim());
            }
            MovieEY movie = new MovieEY(movieID, movieName, movieLanguage, MovieTypeEN.valueOf(movieType),
                    MovieRatingEN.valueOf(movieRating), ShowStatusEN.valueOf(showstatus), synopsis, director, castList,
                    ratings);
            this.masterMovies.add(movie);

        }
    }
    /**
     * Method to write movie objects to text file
     * @throws IOException If there's write error
     */
    public void writeMovie() throws IOException {
        String moviesEPARATOR = " | ";
        String castSEPARATOR = " ~ ";
        String ratingsEPARATOR = " ~ ";
        String filename = this.getCentralManager().getDataFolder().concat("Movies.txt");
        List alw = new ArrayList();
        ArrayList<String> castList;
        ArrayList<String> ratingList;

        MovieEY movie;
        for (int i = 0; i < this.masterMovies.size(); i++) {
            movie = this.masterMovies.get(i);
            StringBuilder st = new StringBuilder();
            st.append(movie.getMovieID().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getName().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getLanguage().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getType());
            st.append(moviesEPARATOR);
            st.append(movie.getMovieRating().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getShowStatus().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getSynopsis().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getDirector().toString().trim());
            st.append(moviesEPARATOR);

            castList = movie.getCast();
            for (int j = 0; j < castList.size(); j++) {
                String cast = castList.get(j);
                st.append(cast.trim());
                st.append(castSEPARATOR);
            }
            st.append(moviesEPARATOR);
            ratingList = movie.getViewerRatingsID();
            for (int j = 0; j < ratingList.size(); j++) {
                String ratingID = ratingList.get(j);
                st.append(ratingID.trim());
                st.append(ratingsEPARATOR);
            }

            alw.add(st.toString());

        }
         ioManager.write(filename, alw);;
    }


}

