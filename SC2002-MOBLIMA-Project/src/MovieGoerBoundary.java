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
                7. Exit Moblima App
                
                Enter choice : 
                 """

        );
        while (!(choice >= 1 && choice <= 7)) {
            choice = this.getInputInt("Please only enter integers between 1 to 4 (inclusive).");
        }
        return choice;
    }

    public void MovieGoerOperations(String userID) throws ParseException {
        int moviGoerChoice = 0;
        while (moviGoerChoice != 7) {
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
                    break;
            }
         }
    }
}