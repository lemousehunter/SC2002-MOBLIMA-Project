import java.util.ArrayList;

public class BDisplayBookingB extends Boundary implements DisplayItemB {
    BookingManager bookingMgr;
    MovieManager movieMgr;
    ScreenManager screenMgr;
    CineplexManager cineplexMgr;

    public BDisplayBookingB(BookingManager bookingMgr, MovieManager movieMgr, ScreenManager screenMgr, CineplexManager cineplexMgr) {
        this.bookingMgr = bookingMgr;
        this.movieMgr = movieMgr;
        this.screenMgr = screenMgr;
        this.cineplexMgr = cineplexMgr;
    }

    @Override
    public void show(String bookingID) {
        Booking booking = this.bookingMgr.getBookingByID(bookingID);
        this.println("================================");
        this.println("Booking: " + bookingID);
        this.println("--------------------------------");
        this.println("UserID: " + booking.getUserID());
        this.println("Movie: " + this.movieMgr.getMovieByID(booking.getMovieID()).getName());
        this.println("Screen: " + this.screenMgr.getScreenByID(booking.getScreenID()).getScreenName());
        this.println("Cinema: " + this.cineplexMgr.getCineplexByID(booking.getCinemaID()).getName());
        this.println("Show Date: " + booking.getDate());
        this.println("Show Time: " + booking.getTime());
        this.println("Total Paid: " + booking.getBookingAmount());
        this.println("--------------------------------");
        this.println("Seats");
        this.println("--------------------------------");
        ArrayList<String> seats = this.bookingMgr.getListOfSeats(bookingID);
        for (String seat: seats) {
            this.println(seat);
        }
    }
}
