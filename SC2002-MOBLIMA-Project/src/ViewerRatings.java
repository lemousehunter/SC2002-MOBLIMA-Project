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

public class ViewerRatings {

	private String review;
	private RatingScale scale;
	private String userID;
	private String movieID;
	String viewerRatingID;
	
	
	/**
	 * @param userID The User ID of the reviewer
	 * @param movieID The Movie ID of the movie being reviewed
	 * @param scale The rating being given to the movie
	 * @param review The review being given to the movie
	 */
	public ViewerRatings(String userID, String movieID,  RatingScale scale, String review) {
		this.userID=userID;
		this.movieID=movieID;
		this.review=review;	
		this.scale=scale;
		viewerRatingID=UUID.randomUUID().toString();
	}
	/**
	 * @param viewerRatingID The ID of the rating
	 * @param userID The User ID of the reviewer
	 * @param movieID The Movie ID of the movie being reviewedD
	 * @param scale The rating being given to the movie
	 * @param review The review being given to the movie
	 */
	public ViewerRatings(String viewerRatingID,String userID, String movieID,  RatingScale scale, String review) {
		this.userID=userID;
		this.movieID=movieID;
		this.review=review;	
		this.scale=scale;
		this.viewerRatingID=viewerRatingID;
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
		public RatingScale getRating()
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
			return viewerRatingID;

		}
}