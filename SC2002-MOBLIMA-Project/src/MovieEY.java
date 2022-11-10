import java.util.*;

/**
 * A Movie Object
 * 
 * <p>
 * A <code>Movie</code> object contains all the parameters used to define 
 * a movie in the project
 * </p>
 * 
 */

public class MovieEY {

	private String movieID;
	private String name;
	private String movieLanguage;
	private MovieTypeEN movieType;
	private MovieRatingEN movieRating;
	private ShowStatus showStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast;
	private ArrayList<String> ratingsID;
	private ArrayList<ReviewE> reviews;
	

	public void setReviews(ArrayList<ReviewE> reviews) {
		this.reviews = reviews;
	}


	/**
	 * Movie Constructor
	 * 
	 * @param movieID The ID of the movie
	 * @param name Movie Name
	 * @param movieLanguage The language spoken in the movie
	 * @param movieType The type of movie (Blockbuster/3D/Documentary)
	 * @param movieRating The rating of the movie (G/PG/PG-13/R/X)
	 * @param showStatus The current status of the movie (ComingSoon/NowShowing/Preview/EndOfShowing)
	 * @param synopsis A brief description of the movie plot
	 * @param director The director of the movie
	 * @param cast The cast of the movie
	 * @param ratingsID The ratings given by users to the movie
	 */
	public MovieEY(String movieID, String name, String movieLanguage, String movieType, MovieRatingEN movieRating, ShowStatus showStatus, String synopsis, String director, ArrayList<String> cast, ArrayList<String> ratingsID) {
		this.movieID = movieID;
		this.name = name;
		this.movieLanguage = movieLanguage;
		this.movieType = MovieTypeEN.valueOf(movieType);
		this.movieRating = movieRating;
		this.showStatus = showStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ratingsID = ratingsID;
	}

	
	/** 
	 * The method displays all the details of the current movie object
	 * 
	 * @param reviews The array of all the ratings given to all the movies
	 */


	
	// /** 
	//  * The method is used to convert the string constant of Ratings to its respective numeric values
	//  * 
	//  * @param scale The object of the RatingScale
	//  * @return The integer value of the rating given to the movie
	//  */
	// private double getNumberRating(RatingScale scale)
	// 	{
	// 		double rating=0;
	// 		switch(scale)
	// 			{
	// 				case ONE: rating= 1.0; break;
	// 				case TWO: rating= 2.0; break;
	// 				case THREE:rating= 3.0; break;
	// 				case FOUR:rating= 4.0; break;
	// 				case FIVE:rating= 5.0; break;
	// 				default: break;
	// 			}
	// 			return rating;
	// 	}

	
	/** 
	 * Get method for the ID of the movie
	 * 
	 * @return The movieID of the current movie object
	 */
	public String getMovieID() {
		return this.movieID;
	}

	
	/** 
	 * Get method for the name of the movie
	 * 
	 * @return The name of the movie
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set method for the name of the movie
	 *
	 * @param name The new name of the movie
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/** 
	 * Get method for the spoken language in the movie
	 * 
	 * @return The spoken language in the movie
	 */
	public String getLanguage() {
		return this.movieLanguage;
	}

	
	/** 
	 * Set method for the language of the movie
	 * 
	 * @param lang The new langauge for the movie
	 */
	public void setLanguage(String lang) {
		this.movieLanguage = lang;
	}

	
	/** 
	 * Get method for the type of the movie
	 * 
	 * @return The type of the movie
	 */
	public MovieTypeEN getType() {
		return this.movieType;
	}
	/**
	 * Get method for the type of the movie in boolean
	 *
	 * @return The boolean type of the movie
	 */
	public Boolean getBoolType() {
		return this.movieType.equals(MovieTypeEN.BLOCKBUSTER) || this.movieType.equals(MovieTypeEN.THREEDIMENSION);
	}

	/**
	 * Set method for the type of the movie
	 * 
	 * @param type The type of the movie
	 */
	public void setType(MovieTypeEN type) {
		this.movieType = type;
	}

	
	/** 
	 * Get method for the rating of the movie
	 * 
	 * @return The movie rating
	 */
	public MovieRatingEN getMovieRating() {
		return this.movieRating;
	}

	/**
	 * Set method for the name of the movie
	 * 
	 * @param movieRating The new rating of the movie
	 */
	public void setMovieRating(MovieRatingEN movieRating) {
		this.movieRating = movieRating;
	}

	
	/** 
	 * Get method for the current status of the movie
	 * 
	 * @return the current show status
	 */
	public ShowStatus getShowStatus() {
		return this.showStatus;
	}

	/**
	 * Set method for the status of the movie
	 *
	 * @param showStatus The new show status of the movie
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}

	
	/** 
	 * Get method for the description of the movie
	 * 
	 * @return The brief description of the movie
	 */
	public String getSynopsis() {
		return this.synopsis;
	}

	
	/** 
	 * Set method for the description of the movie
	 * 
	 * @param synopsis The new brief description of the movie
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	
	/** 
	 * Get method for the director of the movie
	 * 
	 * @return The director of the movie
	 */
	public String getDirector() {
		return this.director;
	}

	
	/** 
	 * Set method for the director of the movie
	 * 
	 * @param director The new director of the movie
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	
	/** 
	 * Get method for the cast of the movie
	 * 
	 * @return The cast of the movie
	 */
	public ArrayList<String> getCast() {
		return this.cast;
	}

	
	/** 
	 * Set method for the cast of the movie
	 * 
	 * @param cast The new cast of the movie
	 */
	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}


	/**
	 * Get method for the ratingsID of the movie
	 *
	 * @return The ratings IDs
	 */
	public ArrayList<String> getViewerRatingsID() {
		return ratingsID;
	}

	/**
	 * Set method for the ratingsID of the movie
	 *
	 * @param ratingsID The ratings IDs of the movie
	 */
	public void addViewerRatingsID(String ratingsID) {
		this.ratingsID.add(ratingsID);
	}

	public MovieTypeEN getMovieType() {
		return movieType;
	}
}