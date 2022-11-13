package Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Controller.MovieManager;
import Controller.MovieManager;
import Controller.MovieGoerManager;
import Controller.ScreenManager;
import Controller.TicketPriceManager;
import Controller.HolidayManager;
import Enum.MovieGoerAgeEN;
import Enum.DayTypeEN;

/**
 * An Entity.TicketEY Object
 * 
 * <p>
 * A <code>Ticket</code> object used 
 * to store all parameters about tickets
 * </p>
 * 
 */
public class TicketEY {
    private MovieManager movieMgr;
    private double price;
    private String movieID;
    private MovieGoerManager movieGoerMgr;
    private String userID;
    private ScreenManager screenMgr;
    private TicketPriceManager ticketPriceManager;
    private String screenID;
    private String date;
    private String time;
    private String seatId;


    private HolidayManager holidayManager;
    private String bookingID;

    /**
     * The constructor for ticket object
     * @param movieID The movieID
     * @param userID The userID
     * @param screenID The screenID
     * @param date The date of the show
     * @param time The time of the show
     * @param seatId The seatID for the show
     * @param price The price of the ticket
     * @param bookingID The bookingID 
     * @param holidayManager The holiday manager(controller) object 
     * @param movieMgr The movie manager(controller) object
     * @param screenMgr The screen manager(controller) object
     * @param moviegoerMgr The MovieGoer manager (controller) object
     * @param ticketPriceManager The Ticket price manager(controller) object
     * @throws ParseException If there's parseException error
     */
    public TicketEY(String movieID, String userID, String screenID, String date, String time, String seatId, double price, String  bookingID, HolidayManager holidayManager, MovieManager movieMgr, ScreenManager screenMgr, MovieGoerManager moviegoerMgr, TicketPriceManager ticketPriceManager) throws ParseException {
        // Passing in controller instances from MainApp
        this.movieMgr = movieMgr;
        this.movieGoerMgr = moviegoerMgr;
        this.screenMgr = screenMgr;
        this.holidayManager = holidayManager;
        this.ticketPriceManager = ticketPriceManager;

        // Class attributes
        this.movieID = movieID;
        this.userID = userID;
        this.screenID = screenID;
        this.date = date;
        this.time = time;
        this.seatId = seatId;
        this.price = price;
        this.bookingID = bookingID;

        if (this.price == -1.0) { // if price was not set, automatically sets it
            this.price = this.computePrice();
        }
    }

    /**
     * Method to get the movieID
     * @return the movieID
     */
    public String getMovieID() {
        return this.movieID;
    }

    /**
     * Method to get the userID
     * @return The userID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Method to get screenID
     * @return the screenID
     */
    public String getScreenID() {
        return  this.screenID;
    }

    /**
     * Method to get date
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Method to get time
     * @return the time
     */
    public String getTime() { return this.time; }
    
    /**
     * Method to get seatID
     * @return the seatID
     */
    public String getSeatId() {
        return seatId;
    }

    /**
     * Method to set price
     * @param price The price 
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method to get price
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * Method to compute price
     * @return the price for the specific ticket
     * @throws ParseException
     */
    public double computePrice() throws ParseException {
        boolean isHoliday = this.holidayManager.isHoliday(this.date); // 1: holiday, 0: not holiday
        boolean isWeekend = this.holidayManager.getWeekend(this.date); // 1: weekend, 0: weekday
        boolean isSpecialDay = isHoliday || isWeekend; // 1: special, 0: weekday
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date time = sdf.parse(this.time);
        boolean isBefore6 = (time.before(sdf.parse("18:00")));

        MovieEY movie = this.movieMgr.getMovieByID(this.movieID);
        ScreenEY screen = this.screenMgr.getScreenByID(this.screenID);
        boolean HallType = screen.getBooleanScreenType(); // 1: Premium Hall, 0: Regular Hall

        MovieGoerEY movieGoer = movieGoerMgr.getUserByID(this.userID);
        String movieGoerAge;
        if (movieGoer == null ){
            movieGoerAge = MovieGoerAgeEN.ADULT.toString();
        }
        else{
            movieGoerAge = movieGoer.getAgeType(); // 1: discounted price, 0: normal price
        }

        double price;
        String dayType;
        if (isSpecialDay){
            dayType = DayTypeEN.HOLIDAY.toString();
        }
        else{
            dayType = DayTypeEN.WEEKDAY.toString();
        }
        // Check Staff configured Ticket prices for any Match. If not found Compute price automatically

        try {
            price = ticketPriceManager.getTicketPrice(dayType, screen.getScreenClass().toString(), movieGoer.getAgeType(), movie.getMovieType().toString() );    
            if (price != -1) {
                return price;
            }
            
        } catch (Exception e) {
            price = -1;
        }

        if (movie.getBoolType()){ // 1: blockbuster, 0: regular movies
            price = 15.0;
        }
        else{
            price = 10.0;
        }

        if (isSpecialDay){
            price *= 1.5; // higher prices charged for shows on weekends
        }
        else {
            if (isBefore6) {
                price *= 0.8; // discount for shows before 6pm on non-weekends and non-holidays
            }
        }

        if (HallType){
            price *= 2; // double price for premium halls
        }

        if(movieGoerAge.equals(MovieGoerAgeEN.STUDENT.toString()) || movieGoerAge.equals(MovieGoerAgeEN.SENIOR.toString())){
            price *= 0.5; // half price for students and seniors
        }
        if(movieGoerAge.equals(MovieGoerAgeEN.CHILD.toString())){
            price = 0; // FOC entrance for children
        }

        return price;
    }
}
