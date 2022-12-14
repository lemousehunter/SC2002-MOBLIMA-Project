package Entity;

import java.util.*;

/**
 * A ViewerRatings object
 * 
 * <p>
 * A <code>ViewerRatings</code> object contains all the parameters necessary
 * for reviews and ratings of a movie
 * </p>
 * 
 */

public class ReviewEY {

	private String review;
	private double scale;
	private String userID;
	private String movieID;
	/**
	 * Holds the ID of the review object
	 */
	String reviewID;
	
	
	/**
	 * Constructor for ReviewEy object(require 4 parameter)
	 * @param userID The User ID of the reviewer
	 * @param movieID The Movie ID of the movie being reviewed
	 * @param scale The rating being given to the movie
	 * @param review The review being given to the movie
	 */
	public ReviewEY(String userID, String movieID, double scale, String review) {
		this.userID=userID;
		this.movieID=movieID;
		this.review=review;	
		this.scale=scale;
		reviewID =UUID.randomUUID().toString();
	}
	/**
	 * Constructor for ReviewEY object (require 5 parameter)
	 * @param viewerRatingID The ID of the rating
	 * @param userID The User ID of the reviewer
	 * @param movieID The Movie ID of the movie being reviewedD
	 * @param scale The rating being given to the movie
	 * @param review The review being given to the movie
	 */
	public ReviewEY(String viewerRatingID, String userID, String movieID, double scale, String review) {
		this.userID=userID;
		this.movieID=movieID;
		this.review=review;	
		this.scale=scale;
		this.reviewID =viewerRatingID;
	}

	
	
		
		/** 
		 * Get method for the User ID
		 * 
		 * @return The User ID
		 */
		public String getUserId()
		{
			return userID;

		}
		
		/** 
		 * Get method for the Movie ID
		 * 
		 * @return The Movie ID
		 */
		public String getMovieId()
		{
			return movieID;
			
		}
		
		/** 
		 * Get method for the review given
		 * 
		 * @return The Review given
		 */
		public String getReview()
		{
			return review;
			
		}
		
		/** 
		 * Get method for the Rating given
		 * 
		 * @return The Rating given
		 */
		public double getRating()
		{
			return scale;
			
		}
		
		/** 
		 * Get method for the viewerRating ID
		 * 
		 * @return The ID of the rating given
		 */
		public String getViewerRatingId()
		{
			return reviewID;

		}
}