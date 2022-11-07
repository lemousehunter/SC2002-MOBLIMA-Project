/**
 * A Booking Object
 * 
 * <p>
 * A <code>Booking</code> object contains all the parameters
 * used to store a booking made by a particular user
 * </p>
 * 
 */

public class Booking {
    private String bookingID;
    private String userID;
    private String movieID;
    private String hallID;
    private String cinemaID;
    private String date;
    private String time;
    private double price;

    /**
     * Booking Constructor
     * 
     * @param bookingID The Booking ID
     * @param userID The User ID
     * @param movieID The Movie ID
     * @param hallID The Hall ID
     * @param cinemaID The Cinema ID
     * @param date The Date
     * @param time The Time
     * @param price The total price
     */
    public Booking(String bookingID, String userID, String movieID, String hallID, String cinemaID, String date, String time, double price){
        this.bookingID = bookingID;
        this.userID = userID;
        this.movieID = movieID;
        this.hallID = hallID;
        this.cinemaID = cinemaID;
        this.date = date;
        this.time = time;
        this.price = price;
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
    public String getHallID() {
        return hallID;
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
     * The method returns the total price of the tickets
     * 
     * @return Price
     */
    public double getPrice() {
        return price;
    }
}
