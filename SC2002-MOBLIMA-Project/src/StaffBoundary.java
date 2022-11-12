import java.util.*;

public class StaffBoundary extends Boundary implements BaseBoundary {
    // Managers
    BookingManager bookingManager;
    ReviewManager reviewManager;

    // Boundaries
    CineplexBoundary cineplexBoundary;
    ScreenBoundary screenBoundary;
    MovieBoundary movieBoundary;
    ShowBoundary showBoundary;
    TicketPriceBoundary ticketPriceBoundary;
    HolidayBoundary holidayBoundary;

    @Override
    public void setManagers() {
        this.bookingManager = this.getCentralManager().getBookingMgr();
        this.reviewManager = this.getCentralManager().getReviewMgr();
    }

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
    public StaffBoundary() {
    }

    public int getMainMenuChoice() {

      return this.getInputInt(
              """

                      ========================= Welcome to Staff App =========================
                      1.  Manage Cineplex                                             \s
                      2.  Manage Screen                                             \s
                      3.  Manage Movies                                           \s
                      4.  Manage Shows                                \s
                      5.  Manage Ticket Prices                               \s
                      6.  Manage Holidays                               \s
                      7.  List Top 5 Movies by Sales                            \s
                      8.  List Top 5 Movies by Ratings                              \s
                      9.  Exit application                                  \s
                      ========================================================================
                      Enter choice:"""
      );
    }

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
                    System.out.println("Top 5 movies by bookings:");
                    for (String moviename : top5Movies) {
                        System.out.println(moviename);
                    }
                    break;
                case 8:
                    this.reviewManager.top5MoviesByViewerRatings();
                    break;
                case 9:
                    break;
            }
        }
    }
}
