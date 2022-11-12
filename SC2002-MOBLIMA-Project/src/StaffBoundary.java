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
