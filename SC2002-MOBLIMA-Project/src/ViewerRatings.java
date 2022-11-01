import java.util.*;
public class ViewerRatings {

	private String review;
	private RatingScale scale;
	private String userID;
	private String movieID;
	String viewerRatingID;
	
	
	public ViewerRatings(String userID, String movieID,  RatingScale scale, String review) {
		this.userID=userID;
		this.movieID=movieID;
		this.review=review;	
		this.scale=scale;
		viewerRatingID=UUID.randomUUID().toString();
	}

	
	
		public String getUserId()
		{
			return userID;

		}
		public String getMovieId()
		{
			return movieID;
			
		}
		public String getReview()
		{
			return review;
			
		}
		public RatingScale getRating()
		{
			return scale;
			
		}
		public String getViewerRatingId()
		{
			return viewerRatingID;

		}
}