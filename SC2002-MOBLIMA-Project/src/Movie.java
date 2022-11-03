import java.util.*;


public class Movie {

	private String movieID;
	private String name;
	private String movieLanguage;
	private MovieType movieType;
	private MovieRating movieRating;
	private ShowStatus showStatus;
	private String synopsis;
	private String director;
	private ArrayList<String> cast;
	private ArrayList<ViewerRatings> ratings;
	

	/**
	 *
	 * @param name
	 * @param movieType
	 * @param movieRating
	 * @param showStatus
	 */
	public Movie(String movieID, String name, String movieLanguage, MovieType movieType, MovieRating movieRating, ShowStatus showStatus, String synopsis, String director, ArrayList<String> cast, ArrayList<ViewerRatings> ratings) {
		this.movieID = movieID;
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

	public void viewMovieDetails(ArrayList<ViewerRatings> reviews) {
		System.out.println("Movie Name: " + this.name);
		System.out.println("Movie Status: " + this.showStatus);
		System.out.println("Movie Type: " + this.movieType);
		System.out.println("Movie Rating: " + this.movieRating);
		System.out.println("Movie Language: " + this.movieLanguage);
		System.out.println("Synopsis: " + this.synopsis);
		System.out.println("Director: " + this.director);
		System.out.println("Cast: " + this.cast);

		int count=0; //this variable counts the total number of reviews for a particular movie and helps us do the count for overall ratings
		double sum_for_overall_rating = 0;
		for(ViewerRatings vr: reviews)
		{
			if(vr.getMovieId().equals(this.movieID))
			{
				System.out.println("Review: " + vr.getReview());
				System.out.println("Rating: " + vr.getRating());
				count++;
				sum_for_overall_rating+=getNumberRating(vr.getRating());
			}
		}

		if(count>1)
		{
			System.out.printf("Overall Ratings: %.1f / 5\n", sum_for_overall_rating/count);
		}
		else
		{
			System.out.println("Overall Ratings: NA");
		}
		throw new UnsupportedOperationException();
	}

	private double getNumberRating(RatingScale scale)
		{
			double rating=0;
			switch(scale)
				{
					case ONE: rating= 1.0; break;
					case TWO: rating= 2.0; break;
					case THREE:rating= 3.0; break;
					case FOUR:rating= 4.0; break;
					case FIVE:rating= 5.0; break;
					default: break;
				}
				return rating;
		}

	public String getMovieID() {
		return this.movieID;
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

	public ArrayList<ViewerRatings> getViewerRating() {
		return ratings;
	}

	/**
	 *
	 * @param viewerRatings
	 */
	public void addViewerRating(ViewerRatings viewerRatings) {
		
		this.ratings.add(viewerRatings);
		throw new UnsupportedOperationException();
	}
}