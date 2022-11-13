import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Boundary Class of Booking
 * 
 * A <code>BookingBoundary</code> object contains all the attributes 
 * and methods required for the interfacing of Bookings
 */

public class BookingBoundary extends Boundary implements BaseBoundary{
    // Managers
    /**
     * The Controller Class of Bookings
     */
    BookingManager bookingManager;

    /**
     * The Controller Class of Screens
     */
    ScreenManager screenManager;

    /**
     * The Controller Class of Cineplexes
     */
    CineplexManager cineplexManager;

    /**
     * The Controller Class of Movies
     */
    MovieManager movieManager;

    /**
     * The Controller Class of Shows
     */
    ShowManager showManager;


    // Boundaries
    /**
     * The Boundary Class of Cineplexes
     */
    CineplexBoundary cineplexBoundary;

    /**
     * The Boundary Class of Movies
     */
    MovieBoundary movieBoundary;

    /**
     * The Boundary Class of Shows
     */
    ShowBoundary showBoundary;


    /**
     * Default Constructor which creates an object of class BookingBoundary
     */
    public BookingBoundary() {
    }

    /**
     * Method to set all the controllers classes taken as attributes
     */
    @Override
    public void setManagers() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.bookingManager = centralManager.getBookingMgr();
        this.screenManager = centralManager.getScreenMgr();
        this.cineplexManager = centralManager.getCineplexMgr();
        this.movieManager = centralManager.getMovieMgr();
        this.showManager = centralManager.getShowMgr();
    }

    /**
     * Method to set all the boundaries taken as attributes
     */
    @Override
    public void setBoundaries() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.cineplexBoundary = centralManager.getCineplexBoundary();
        this.movieBoundary = centralManager.getMovieBoundary();
        this.showBoundary = centralManager.getShowBoundary();
    }

    
    /** 
     * Method to select the cineplex of choice
     * 
     * @return the Cinema ID
     */
    public String getCineplexChoice() {
        this.print("Please enter the corresponding index of the cinema you would like to visit");
        this.cineplexBoundary.printAllCineplexes();
        return this.cineplexManager.convertIDX2CineplexID(this.getScanner().nextInt());
    }

    
    /** 
     * Method to view movie details or select a movie to book 
     * 
     * @return The movie ID
     */
    public String getMovieChoice() { // converts idx from user input to movieID
        int choice = this.getInputInt(
                """
                         Would you like to:
                        1) View Movie Details
                        2) Book Movie?
                        """);
        if (choice == 1) {
            this.println("Which movie would you like to view details for? Please enter the corresponding integer.");
            this.movieBoundary.printMovieList();
            int viewMovie = this.getScanner().nextInt();
            String movieID = this.movieManager.getMovieIDFromCurrentShowingIDX(viewMovie);
            this.movieBoundary.viewMovieDetails(movieID);
            this.getMovieChoice(); // calls itself again to emulate jumping back to prev menu
        } else {
            this.print("Which movie would you like to book for? Please enter the corresponding integer.");
            this.movieBoundary.printMovieList();
            int idx = this.getScanner().nextInt();
            return this.movieManager.getMovieIDFromCurrentShowingIDX(idx);
        }
        return "";
    }

    
    /** 
     * Method to get show dates
     * 
     * @return the show dates
     */
    public String getShowDate(String cineplex, String movieID) { // prints out available show dates, gets show date from user
        this.print("Please enter the corresponding index of the date you would like to book");
        this.showBoundary.printShowDates(cineplex,movieID);
        int viewShow = this.getScanner().nextInt();
        return this.showManager.getShowIDFromShowDateIDX(cineplex,movieID,viewShow);
    }

    
    /** 
     * Method to get show time
     * @return show time
     */
    public ArrayList<String> getShowTime(String cineplex, String movieID, String showDate) { // prints out available show timings, returns showTime, screenID
        this.print("Please enter the corresponding index of the time you would like to book");
        this.showBoundary.printShowTimes(cineplex,movieID,showDate);
        int viewShow = this.getScanner().nextInt();
        return this.showManager.getShowIDFromShowTimeIDX(cineplex,movieID,showDate,viewShow);    }

    
    /** 
     * Method get total number of tickets to buy
     * @return Number of tickets
     */
    public Integer getNumTickets() {
        return this.getInputInt("How many tickets would you like to book? ");
    }
    
    /** 
     * Method to show all the details of the booking made
     * 
     * @param bookingID The unique ID of the booking
     */
    public void showBooking(String bookingID) {
        BookingEY booking = bookingManager.getBookingByID(bookingID);

        this.println("================================");
        this.println("Booking: " + bookingID);
        this.println("--------------------------------");
        this.println("UserID: " + booking.getUserID());
        this.println("Movie: " + this.movieManager.getMovieByID(booking.getMovieID()).getName());
        this.println("Screen: " + this.screenManager.getScreenByID(booking.getScreenID()).getScreenName());
        this.println("Cinema: " + this.cineplexManager.getCineplexByID(booking.getCinemaID()).getName());
        this.println("Show Date: " + booking.getDate());
        this.println("Show Time: " + booking.getTime());
        this.println("Total Paid: " + booking.getBookingAmount());
        this.println("--------------------------------");
        this.println("Seats");
        this.println("--------------------------------");
        ArrayList<String> seats = this.bookingManager.getListOfSeats(booking);
        for (String seat: seats) {
            this.println(seat);
        }
    }

    
    /** 
     * Method to create a ticket
     * @param userID The unique ID of the user
     * @throws ParseException
     */
    public void BookingOperations(String userID) throws ParseException {
        String cineplex = this.getCineplexChoice();
        String movieID = this.getMovieChoice();
        String showDate = this.getShowDate(cineplex, movieID);
        ArrayList<String> container = this.getShowTime(cineplex, movieID,showDate);
        String showTime = container.get(0);
        String screenID = container.get(1);
        String showID   = container.get(2);
        Integer numTickets = this.getNumTickets();

        
        ArrayList<String> seatIDs = this.getShowSeat(showID,numTickets);
        

        String bookingID = this.bookingManager.BookTicket(userID, movieID, showDate, showTime, cineplex, screenID, seatIDs);
        this.showManager.setSeatOccupied(showID, seatIDs);
        
        this.println("Your Tickets have been booked successfully!!");
        this.showBooking(bookingID);
    }


    /**
     * Method to return the array of all seat IDs selected to buy
     * @param showID The show selected
     * @param numTickets The total number of tickets needed to be bought
     * @return List of all seat IDs selected to buy
     */
    private ArrayList<String> getShowSeat(String showID, Integer numTickets) {
        this.showBoundary.ShowSeatLayout(showID);

        ArrayList<String> seatIDs = new ArrayList<String>();
        String seatID="";
        this.println("Please enter the seat numbers  : \n");

        for (int i=1 ; i <= numTickets;i++){

            seatID = this.getInputLine(i+". ");
            if (i == 1) {
                seatID =this.getScanner().nextLine();
            }
            seatIDs.add(seatID);
        }
        

        return new ArrayList<String>(seatIDs);
    }

    public void viewMyBookings(String userID) {
        this.println("==========View My Bookings=========");
        for (String bookingID: this.bookingManager.getAllBookingsList(userID)) {
            this.showBooking(bookingID);
        }
        this.println("===================================");
    }
    

}
