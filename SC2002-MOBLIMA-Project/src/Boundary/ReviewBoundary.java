package Boundary;

import Controller.MovieManager;
import Controller.ReviewManager;

import java.util.*;
/**
 * A Review Boundary Object
 * 
 * <p>
 * A <code>ReviewBoundary</code> object used 
 *  process all Review input and output
 * </p>
 * 
 */
public class ReviewBoundary extends Boundary implements BaseBoundary{
    /**
     * To contain reviewManager object to process review objects
     */
    ReviewManager reviewManager;
    /**
     * To contain movieManager object to process review objects
     */
    MovieManager movieManager;
    /**
     * Constructor for ReviewBoundary
     */
    public ReviewBoundary() {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.reviewManager = this.getCentralManager().getReviewMgr();
        this.movieManager = this.getCentralManager().getMovieMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {

    }

    /**
     * Method to get movie rating scale
     * @param movieID The movieID
     * @return The scale
     */
    public double getScale(String movieID) {
        double scale = 0;
        String movieName = this.movieManager.getMovieByID(movieID).getName();

        while (scale > 5 || scale < 1) {
            try {
                scale = this.getInputDouble("Please enter a rating between (1-5) for " + movieName+" : ");
            } catch (Exception e) {
                this.println("Enter Ratings between 1-5 Value only");
            }
        }

        return scale;
    }

    /**
     * Method to add review
     * @param userID The userID
     * @param movieID The movieID
     */
    public void addReview(String userID, String movieID) {
        String movieName = this.movieManager.getMovieByID(movieID).getName();
        String review = this.getInputLine("Please enter your review for " + movieName + " : ");
        double scale = this.getScale(movieID);
        this.reviewManager.addReview(userID, movieID, scale, review);

        this.println("\nReview for the movie " + movieName + " has been added");
    }

    /**
     * Method to print average rating
     * @param movieID The movieID
     */
    public void AvgRatingPrint(String movieID) {

        double sum = this.reviewManager.getAvgRating(movieID);
        if (sum > 0) {
            this.println("Average Rating is " + String.valueOf(sum));

        } else {
            this.println("NA");
        }
    }

    /**
     * Method to print top 5 movies
     */
    public void Top5MovPrint() {
        ArrayList<String> top5movies = this.reviewManager.top5MoviesByViewerRatings();
        this.println("=========================================");
        this.println("----------Top 5 Movies By Rating---------");
        if (top5movies != null && top5movies.size() == 5) {
            System.out.println("\nTop 5 movies by Average Viewer Ratings");
            System.out.println("--------------------------------------");
            for (int i = 1; i <= top5movies.size(); i++) {
                String movieName = top5movies.get(i - 1);
                String movieID = this.movieManager.Name2ID(movieName);
                String rating  = String.valueOf(this.reviewManager.getAvgRating(movieID));
                this.println(i + ". " + movieName + " - Rating: " + rating + "/5");
            }

        } else {
            this.println("NA");
        }
        this.println("=========================================");
    }
}