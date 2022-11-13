package Boundary;

import Controller.BookingManager;
import Controller.MovieManager;
import Entity.MovieEY;

import java.text.ParseException;
import java.util.ArrayList;
/**
 * A MovieGoer Boundary Object
 * 
 * <p>
 * A <code>MovieGoerBoundary</code> object used 
 *  process all MovieGoer input and output
 * </p>
 * 
 */
/**
 * @author harvi
 *
 */
public class MovieGoerBoundary extends Boundary implements BaseBoundary {
    // Manager
    /**
     * A movie manager object to manage movies
     */
    MovieManager movieManager;
    /**
     * A booking manager object to manage bookings
     */
    BookingManager bookingManager;

    // Boundaries
    /**
     * A movie boundary object to process movie related input and output
     */
    MovieBoundary movieBoundary;
    /**
     * A booking boundary object to process booking related input and output
     */
    BookingBoundary bookingBoundary;
    /**
     * A review boundary object to manage review and rating related input and output
     */
    ReviewBoundary reviewBoundary;

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.movieManager = this.getCentralManager().getMovieMgr();
        this.bookingManager = this.getCentralManager().getBookingMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {
        this.bookingBoundary = this.getCentralManager().getBookingBoundary();
        this.movieBoundary = this.getCentralManager().getMovieBoundary();
        this.reviewBoundary = this.getCentralManager().getReviewBoundary();
    }

    /**
     * Method to write movie review
     * @param userID The userID
     */
    public void writeReview(String userID) {
        String movieName = this.movieBoundary.getMovieName();
        MovieEY movie = this.movieManager.searchMovie(movieName);
        if (movie == null) {
            this.println("\nError : You have entred a Movie that doesnot exist\n");
        }
        else {
            this.reviewBoundary.addReview(userID, movie.getMovieID());
        }
    }
	/**
     * Method to print top 5 movies revenue
     */
    public void listTop5MoviesRevenue() {
        this.println("=========================================");
        this.println("---------Top 5 Movies By Revenue---------");
        int count = 1;
        ArrayList<String> top5List = this.bookingManager.getTop5Movies();
        for (String movieID: top5List) {
            String movieName = this.movieManager.getMovieByID(movieID).getName();
            this.println(count + ": " + movieName);
            count++;
        }
        this.println("=========================================");
    }

    /**
     * Method to display menu and get MovieGoer choice
     * @return The choice
     */
    public int getMovieGoerMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
                """
                Movie goer menu:
                
                1. Search Movies
                2. List shows of a Movie
                3. Book Ticket
                4. Write Review
                5. List Top 5 Movies by Sales
                6. List Top 5 Movies by Review
                7. View my Bookings
                8. Exit Moblima App
                
                Enter choice : 
                 """

        );
        while (!(choice >= 1 && choice <= 8)) {
            choice = this.getInputInt("Please only enter integers between 1 to 8 (inclusive).");
        }
        return choice;
    }

    /**
     * Method to perform MovieGoer operations movie goer choice
     * @param userID The userID
     * @throws ParseException If there's error
     */
    public void MovieGoerOperations(String userID) throws ParseException {
        int moviGoerChoice = 0;
        while (moviGoerChoice != 8) {
            moviGoerChoice = this.getMovieGoerMenuChoice();
            switch (moviGoerChoice) {
                case 1:
                    this.movieBoundary.searchMovie();
                    break;
                case 2:
                    this.movieBoundary.listAllShows();
                    break;
                case 3:
                    this.bookingBoundary.BookingOperations(userID);
                    break;
                case 4:
                    this.writeReview(userID);
                    break;
                case 5:
                    this.listTop5MoviesRevenue();
                    break;
                case 6:
                    this.reviewBoundary.Top5MovPrint();
                    break;
                case 7:
                    this.bookingBoundary.viewMyBookings(userID);
                    break;

                case 8:
                    break;
            }
         }
    }
}