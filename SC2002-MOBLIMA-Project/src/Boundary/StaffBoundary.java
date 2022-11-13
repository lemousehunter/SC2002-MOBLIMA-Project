package Boundary;

import Controller.BookingManager;
import Controller.MovieManager;
import Controller.ReviewManager;
import Entity.CentralManagerEY;

import java.util.*;
/**
 * A Staff Boundary.Boundary Object
 * 
 * <p>
 * A <code>Boundary.StaffBoundary</code> object used
 *  process all Staff input and output
 * </p>
 * 
 */
public class StaffBoundary extends Boundary implements BaseBoundary {
    // Managers
    /**
     * contains	bookingManager object to process all booking object
     */
    BookingManager bookingManager;
    /**
     * contains reviewManager object to process all  review  object
     */
    ReviewManager reviewManager;
    /**
     * contains	movieManager object to process all movie  object
     */
    MovieManager movieManager;

    // Boundaries
    /**
     * contains	cineplexBoundary object to process all cineplex input and output
     */
    CineplexBoundary cineplexBoundary;
    /**
     * contains	screenBoundary	object to process all  screen  input and output
     */
    ScreenBoundary screenBoundary;
    /**
     * contains	movieBoundary object to process all  movie  input and output
     */
    MovieBoundary movieBoundary;
    /**
     * contains	showBoundary object to process all  show  input and output
     */
    ShowBoundary showBoundary;
    /**
     * contains	ticketPriceBoundary	object to process all ticket  input and output
     */
    TicketPriceBoundary ticketPriceBoundary;
    /**
     * contains	holidayBoundary	object to process all holiday input and output
     */
    HolidayBoundary holidayBoundary;

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.bookingManager = this.getCentralManager().getBookingMgr();
        this.reviewManager = this.getCentralManager().getReviewMgr();
        this.movieManager = this.getCentralManager().getMovieMgr();

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.cineplexBoundary = centralManager.getCineplexBoundary();
        this.screenBoundary = centralManager.getScreenBoundary();
        this.movieBoundary = centralManager.getMovieBoundary();
        this.showBoundary = centralManager.getShowBoundary();
        this.ticketPriceBoundary = centralManager.getTicketPriceBoundary();
        this.holidayBoundary = centralManager.getHolidayBoundary();
    }
    /**
     * Constructor for Boundary.StaffBoundary object
     */
    public StaffBoundary() {
    }

    /**
     * Method to print main menu and get main menu choice
     * @return The main menu choice
     */
    public int getMainMenuChoice() {

      return this.getInputInt(
        "\n========================= Welcome to Staff App =========================\n" +
        "1.  Manage Cineplex                                              \n" +
        "2.  Manage Screen                                              \n" +
        "3.  Manage Movies                                            \n" +
        "4.  Manage Shows                                 \n" +
        "5.  Manage Ticket Prices                                \n" +
        "6.  Manage Holidays                                \n" +
        "7.  List Top 5 Movies by Sales                             \n" +
        "8.  List Top 5 Movies by Ratings                               \n" +
        "9.  Exit application                                   \n" +
        "========================================================================\n" +
        "Enter choice: "
      );
    }

    /**
     * Method to perform staff operations based on main menu choice
     * (manage cineplex, screen,movies,shows, ticket prices, holidays)
     * (list top 5 movies based on sales and ratings, exit application)
     */
    public void staffOperations() {
        int choice = 0;
        while (choice != 9) {
            choice = this.getMainMenuChoice();
            if (choice < 0 | choice > 9) {
                this.println("Enter choice betwen 1-9 values only");
                continue;
            }
            switch (choice) {
                case 1:
                    this.cineplexBoundary.cineplexOperations();
                    break;
                case 2:
                    this.screenBoundary.screenOperations();
                    break;
                case 3:
                    this.movieBoundary.movieOperations();
                    break;
                case 4:
                    this.showBoundary.showOperations();
                    break;
                case 5:
                    this.ticketPriceBoundary.ticketPriceOperations();
                    break;
                case 6:
                    this.holidayBoundary.holidayOperations();
                    break;
                case 7:
                    ArrayList<String> top5Movies = this.bookingManager.getTop5Movies();
                    this.println("Top 5 movies by bookings:");
                    for (String movieId : top5Movies) {
                        System.out.println(this.movieManager.getMovieByID(movieId));
                        this.println("Total Revenue: SGD$" + this.bookingManager.getTotalSales(movieId));
                    }
                    break;
                case 8:
                    Top5MovPrint(this.reviewManager.top5MoviesByViewerRatings());
                    break;
                case 9:
                    break;
            }
        }
    }

    /**
     * Method to print top 5 movies by viewer rating
     * @param top5movies The top 5 movies based on viewer rating
     */
    public void Top5MovPrint(ArrayList<String> top5movies)
    {
      if(top5movies!=null&&top5movies.size()==5)
      {
        System.out.println("\nTop 5 movies by Average Viewer Ratings");
        System.out.println("--------------------------------------");
        for (int i=1; i<=top5movies.size();i++){
          System.out.println(i + ". " + top5movies.get(i-1));
        }
        

      }else
      {
        System.out.println("NA");
      }
    }
}
