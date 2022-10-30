import java.util.*;
public class Movie {

	private String name;
	private String movieLanguage;
	private MovieType movieType;
	private MovieRating movieRating;
	private ShowStatus showStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast;
	private ArrayList<ViewerRatings> ratings;

	public Movie() {
		name = "";
		movieLanguage = "";
		movieType = MovieType.BLOCKBUSTER;
		movieRating = MovieRating.G;
		showStatus = ShowStatus.COMINGSOON;
		synopsis = "";
		director = "";
		cast = new ArrayList<String>();
		ratings = new ArrayList<ViewerRatings>();
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param name
	 * @param movieType
	 * @param movieRating
	 * @param showStatus
	 */
	public Movie(String name, String movieLanguage, MovieType movieType, MovieRating movieRating, ShowStatus showStatus, String synopsis, String director, ArrayList<String> cast, ArrayList<ViewerRatings> ratings) {
		this.name = name;
		this.movieLanguage = movieLanguage;
		this.movieType = movieType;
		this.movieRating = movieRating;
		this.showStatus = showStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ratings = ratings;
		throw new UnsupportedOperationException();
	}

	public void viewMovieDetails() {
		System.out.println("Movie Name: " + this.name);
		System.out.println("Movie Status: " + this.showStatus);
		System.out.println("Movie Type: " + this.movieType);
		System.out.println("Movie Rating: " + this.movieRating);
		System.out.println("Movie Language: " + this.movieLanguage);
		System.out.println("Synopsis: " + this.synopsis);
		System.out.println("Director: " + this.director);
		System.out.println("Cast: " + this.cast);
		for(ViewerRatings vr: ratings) {
			System.out.println(vr.getReview());
			System.out.println(vr.getRating());
		}
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return this.movieLanguage;
	}

	public void setLanguage(String lang) {
		this.movieLanguage = lang;
	}

	public MovieType getType() {
		return this.movieType;
		//throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param type
	 */
	public void setType(MovieType type) {
		this.movieType = type;
		throw new UnsupportedOperationException();
	}

	public MovieRating getMovieRating() {
		return this.movieRating;
		//throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param movieRating
	 */
	public void setMovieRating(MovieRating movieRating) {
		this.movieRating = movieRating;
		throw new UnsupportedOperationException();
	}

	public ShowStatus getShowStatus() {
		return this.showStatus;
	}

	/**
	 *
	 * @param showStatus
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public ArrayList<String> getCast() {
		return this.cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public ArrayList<ViewerRatings> getRatings() {
		return this.ratings;
	}

	public void setRatings(ArrayList<ViewerRatings> ratings) {
		this.ratings = ratings;
	}


	/**
	 *
	 * @param viewerRatings
	 */
	public void addViewerRating(ViewerRatings viewerRatings) {
		ratings.add(viewerRatings);
		throw new UnsupportedOperationException();
	}

	public boolean getMovieType() {
	}
}