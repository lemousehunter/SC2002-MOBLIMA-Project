import java.text.ParseException;
import java.util.ArrayList;
/**
 * A Booking Object
 * 
 * <p>
 * A <code>Booking</code> object contains all the attributes
 * used to store a booking made by a particular user
 * </p>
 * 
 */

public class BookingEY {
    /**
     * The Booking ID
     */
    private String bookingID;
    /**
     * The user ID
     */
    private String userID;
    /**
     * The movie ID
     */
    private String movieID;
    /**
     * The screen ID
     */
    private String screenID;
    /**
     * The cinema ID
     */
    private String cinemaID;
    /**
     * Date of the booking
     */
    private String date;
    /**
     * Time of the show booked
     */
    private String time;
    /**
     * The seat numbers
     */
    private ArrayList<String> seatIds;
    /**
     * The total booking amount
     */
    private double bookingAmount;
    /**
     * Array of all tickets bought
     */
    private ArrayList<TicketEY> tickets;


    /**
     * Creates new booking with the given attributes
     * 
     * @param bookingID This Booking ID
     * @param userID This User ID
     * @param movieID This Movie ID
     * @param screenID This Screen ID
     * @param cinemaID This Cinema ID
     * @param date This Date
     * @param time This Time
     * @param seatIds This SeatIds
     * @param bookingAmount This total price
     */
    public BookingEY(String bookingID, String userID, String movieID, String screenID, String cinemaID, String date, String time, ArrayList<String>  seatIds, double bookingAmount, HolidayManager holidayManager, MovieManager movieMgr, ScreenManager screenMgr, MovieGoerManager movieGoerMgr, TicketPriceManager ticketPriceManager) throws ParseException {
        this.bookingID = bookingID;
        this.userID = userID;
        this.movieID = movieID;
        this.screenID = screenID;
        this.cinemaID = cinemaID;
        this.date = date;
        this.time = time;
        this.bookingAmount = bookingAmount;
        int numTickets = seatIds.size();
        this.tickets = new ArrayList<TicketEY>();
        for (int i=0; i<numTickets; i++)
        {
            this.tickets.add(new TicketEY(movieID, userID, screenID, date, time, seatIds.get(i), -1, bookingID, holidayManager, movieMgr, screenMgr, movieGoerMgr, ticketPriceManager));
        }
        if (this.bookingAmount == -1) {         // prime methods would always have bookingAmount
            this.bookingAmount = this.computeBookingAmount();
        }
    }

    /** 
     * The method returns the Booking ID
     * 
     * @return Booking ID
     */
    public String getBookingID() {
        return bookingID;
    }

    
    /** 
     * The method returns the User ID
     * 
     * @return User ID
     */
    public String getUserID() {
        return userID;
    }

    
    /** 
     * The method returns the Movie ID
     * 
     * @return Movie ID
     */
    public String getMovieID() {
        return movieID;
    }

    
    /** 
     * The method returns the Screen ID
     * 
     * @return Screen ID
     */
    public String getScreenID() {
        return screenID;
    }

    
    /** 
     * The method returns the Cinema ID
     * 
     * @return Cinema ID
     */
    public String getCinemaID() {
        return cinemaID;
    }

    
    /** 
     * The method returns the date
     * 
     * @return Date
     */
    public String getDate() {
        return date;
    }

    
    /** 
     * The method returns the time
     * 
     * @return Time
     */
    public String getTime() {
        return time;
    }

    
    /**
     * The method returns the all the tickets bought
     * 
     * @return Tickets bought
     */
    public ArrayList<TicketEY> getTickets() {
        return tickets;
    }
    
    /** 
     * The method returns the total price of the tickets
     * 
     * @return Total Booking Amount
     */
    public double getBookingAmount() {
        return bookingAmount;
    }
    
    /** 
     * The method returns the total booking amount
     * @return total booking amount
     */
    public double computeBookingAmount() {
        double bookingAmount=0;
        for (TicketEY ticket : this.tickets) {
            bookingAmount += ticket.getPrice();
        }
        return bookingAmount;
    }
}
