import java.text.ParseException;
import java.util.ArrayList;
/**
 * A Booking Object
 * 
 * <p>
 * A <code>Booking</code> object contains all the parameters
 * used to store a booking made by a particular user
 * </p>
 * 
 */

public class BookingEY {
    private String bookingID;
    private String userID;
    private String movieID;
    private String screenID;
    private String cinemaID;
    private String date;
    private String time;
    private ArrayList<String> seatIds;
    private double bookingAmount;
    private ArrayList<TicketEY> tickets;


    /**
     * Booking Constructor
     * 
     * @param bookingID The Booking ID
     * @param userID The User ID
     * @param movieID The Movie ID
     * @param screenID The Screen ID
     * @param cinemaID The Cinema ID
     * @param date The Date
     * @param time The Time
     * @param seatIds The SeatIds
     * @param bookingAmount The total price
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
     * The method returns the Hall ID
     * 
     * @return Hall ID
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
     * @return Tickets
     */
    public ArrayList<TicketEY> getTickets() {
        return tickets;
    }
    
    /** 
     * The method returns the total price of the tickets
     * 
     * @return Booking Amount
     */
    public double getBookingAmount() {
        return bookingAmount;
    }
    public double computeBookingAmount() {
        double bookingAmount=0;
        for (TicketEY ticket : this.tickets) {
            bookingAmount += ticket.getPrice();
        }
        return bookingAmount;
    }
}
