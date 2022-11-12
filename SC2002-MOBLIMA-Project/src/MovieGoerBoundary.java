import java.text.ParseException;
import java.util.ArrayList;

public class MovieGoerBoundary extends Boundary implements BaseBoundary {
    // Manager
    MovieManager movieManager;
    BookingManager bookingManager;

    // Boundaries
    MovieBoundary movieBoundary;
    BookingBoundary bookingBoundary;
    ReviewBoundary reviewBoundary;

    @Override
    public void setManagers() {
        this.movieManager = this.getCentralManager().getMovieMgr();
        this.bookingManager = this.getCentralManager().getBookingMgr();
    }

    @Override
    public void setBoundaries() {
        this.bookingBoundary = this.getCentralManager().getBookingBoundary();
        this.movieBoundary = this.getCentralManager().getMovieBoundary();
        this.reviewBoundary = this.getCentralManager().getReviewBoundary();
    }

    public void writeReview(String userID) {
        String movieName = this.movieBoundary.getMovieName();
        MovieEY movie = this.movieManager.searchMovie(movieName);
        this.reviewBoundary.addReview(userID, movie.getMovieID());
    }

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

    public void MovieGoerOperations(String userID) throws ParseException {
        int choice = this.getInputInt(
        """
        Movie goer menu:
        
        1. Search Movies
        2. List shows of a Movie
        3. Book Ticket
        4. Write Review
        5. List Top 5 Movies by Sales
        6. List Top 5 Movies by Review
        """);

        while (choice < 1 || choice > 6) {
            choice = this.getInputInt("Invalid choice. Please enter integers between 1 to 6 (inclusive) only.");
        }

        switch (choice) {
            case 1:
                this.movieBoundary.searchMovie();
            case 2:
                this.movieBoundary.listAllShows();
            case 3:
                this.bookingBoundary.BookingOperations(userID);
            case 4:
                this.writeReview(userID);
            case 5:
                this.listTop5MoviesRevenue();
            case 6:
                this.reviewBoundary.Top5MovPrint();
        }
    }
}