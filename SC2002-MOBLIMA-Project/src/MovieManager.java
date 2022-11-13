import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class MovieManager extends Manager implements BaseManager {

    // managers
    private IoManager ioManager;

    // arrays
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<ReviewEY> masterRatings;


    public MovieManager () {

    }

    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }
    
    @Override
    public void setMasterLists() {
        this.masterMovies = this.getCentralManager().getMasterMovies();
        this.masterRatings= this.getCentralManager().getMasterRatings();             
    }

    public MovieEY getMovieByID(String movieID) { // returns movie object associated with specified movieID, else return null
        for(MovieEY m: this.masterMovies) {
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

    public MovieEY getMovieByName(String movieName) {
        if (movieName.isEmpty()) { return null;}
        for (MovieEY movie: this.masterMovies) {
            if (movie.getName().equalsIgnoreCase(movieName)) {
                return movie;
            }
        }
        return null;    
    }

    public String ID2Name(String movieID) {
        return this.getMovieByID(movieID).getName();
    }

    public String Name2ID(String movieName) {
        return this.getMovieByName(movieName).getMovieID();
    }

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

