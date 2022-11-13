package Entity;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import Entity.*;
import Controller.*;
import Boundary.*;

/**
 * A Entity.CentralManagerEY Object
 * 
 * <p>
 * A <code>Entity.CentralManagerEY</code> object contains all the master lists, and instances of all boundaries and controllers
 * </p>
*/
public class CentralManagerEY {
    // Master Lists
    private ArrayList<UserEY> masterUsers;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidays;
    private ArrayList<ReviewEY> masterRatings;
    private ArrayList<MovieGoerEY> masterMovieGoers;
    private ArrayList<TicketPrice> masterTicketPrices;

    // Master Data folder obtained from JVM -Ddatafolder argument
    private String dataFolder;

    // Managers
    private BookingManager bookingMgr;
    private CineplexManager cineplexMgr;
    private HolidayManager holidayMgr;
    private MovieManager movieMgr;
    private ReviewManager reviewMgr;
    private ScreenManager screenMgr;
    private ShowManager showMgr;
    private TicketPriceManager ticketPriceMgr;
    private MovieGoerManager movieGoerMgr;
    private UserManager userMgr;

    private IoManager ioManager;


    // Boundaries
    private BookingBoundary bookingBoundary;
    private CineplexBoundary cineplexBoundary;
    private HolidayBoundary holidayBoundary;
    private MovieBoundary movieBoundary;
    private ReviewBoundary reviewBoundary;
    private ScreenBoundary screenBoundary;
    private TicketPriceBoundary ticketPriceBoundary;
    private ShowBoundary showBoundary;
    private MovieGoerBoundary movieGoerBoundary;

    private StaffBoundary staffBoundary;

    /**
     * Method to create an object of Entity.CentralManagerEY
     */
    public CentralManagerEY() throws IOException, ParseException {

        // Create Master lists

        this.masterUsers = new ArrayList<UserEY>();
        this.masterCineplexes = new ArrayList<CineplexEY>();
        this.masterScreens = new ArrayList<ScreenEY>();
        this.masterBookings = new ArrayList<BookingEY>();
        this.masterShows = new ArrayList<ShowEY>();
        this.masterMovies = new ArrayList<MovieEY>();
        this.masterHolidays = new ArrayList<String>();
        this.masterRatings = new ArrayList<ReviewEY>();
        this.masterTicketPrices = new ArrayList<TicketPrice>();
        this.masterMovieGoers = new ArrayList<MovieGoerEY>();

        // Master data folder derived from datafolder property
        this.dataFolder= System.getProperty("dataFolder");
        if (this.dataFolder== null) {
            this.dataFolder = "";
            // folder is not defined via relative path from cwd
            this.dataFolder = System.getProperty("user.dir") + "/InputData/";
        }

        // instantiate all managers
        this.movieGoerMgr = new MovieGoerManager();
        this.movieGoerMgr.setCentralManager(this);
        
        this.cineplexMgr = new CineplexManager();
        this.cineplexMgr.setCentralManager(this);
        
        this.holidayMgr = new HolidayManager();
        this.holidayMgr.setCentralManager(this);
        
        this.movieMgr = new MovieManager();
        this.movieMgr.setCentralManager(this);
        
        this.reviewMgr = new ReviewManager();
        this.reviewMgr.setCentralManager(this);

        this.screenMgr = new ScreenManager();
        this.screenMgr.setCentralManager(this);

        this.showMgr = new ShowManager();
        this.showMgr.setCentralManager(this);

        this.ticketPriceMgr = new TicketPriceManager();
        this.ticketPriceMgr.setCentralManager(this);

        this.movieGoerMgr = new MovieGoerManager();
        this.movieGoerMgr.setCentralManager(this);

        this.userMgr = new UserManager();
        this.userMgr.setCentralManager(this);

        this.ioManager = new IoManager();
        this.ioManager.setCentralManager(this);

        this.bookingMgr = new BookingManager();
        this.bookingMgr.setCentralManager(this);

        // set all master listts

        this.bookingMgr.setMasterLists();
        this.cineplexMgr.setMasterLists();
        this.holidayMgr.setMasterLists();
        this.movieMgr.setMasterLists();
        this.reviewMgr.setMasterLists();
        this.screenMgr.setMasterLists();
        this.showMgr.setMasterLists();
        this.ticketPriceMgr.setMasterLists();
        this.movieGoerMgr.setMasterLists();
        this.ioManager.setMasterLists();
        this.userMgr.setMasterLists();

        // invoke setManager() to get the other manager instances required
        
        this.bookingMgr.setManagers();
        this.cineplexMgr.setManagers();
        this.holidayMgr.setManagers();
        this.movieMgr.setManagers();
        this.reviewMgr.setManagers();
        this.screenMgr.setManagers();
        this.showMgr.setManagers();
        this.ticketPriceMgr.setManagers();
        this.movieGoerMgr.setManagers();
        this.ioManager.setManagers();
        this.userMgr.setManagers();

        // instantiate all boundary classes
        this.bookingBoundary = new BookingBoundary();
        this.bookingBoundary.setCentralManager(this);

        this.cineplexBoundary = new CineplexBoundary();
        this.cineplexBoundary.setCentralManager(this);

        this.holidayBoundary = new HolidayBoundary();
        this.holidayBoundary.setCentralManager(this);

        this.movieBoundary = new MovieBoundary();
        this.movieBoundary.setCentralManager(this);

        this.reviewBoundary = new ReviewBoundary();
        this.reviewBoundary.setCentralManager(this);

        this.screenBoundary = new ScreenBoundary();
        this.screenBoundary.setCentralManager(this);

        this.ticketPriceBoundary = new TicketPriceBoundary();
        this.ticketPriceBoundary.setCentralManager(this);

        this.showBoundary = new ShowBoundary();
        this.showBoundary.setCentralManager(this);

        this.movieGoerBoundary = new MovieGoerBoundary();
        this.movieGoerBoundary.setCentralManager(this);

        this.staffBoundary = new StaffBoundary();
        this.staffBoundary.setCentralManager(this);

        // invoke setManagers()  & setBundaries to get the other manager instances required
        
        this.bookingBoundary.setBoundaries();       this.bookingBoundary.setManagers();
        this.cineplexBoundary.setBoundaries();      this.cineplexBoundary.setManagers();
        this.holidayBoundary.setBoundaries();       this.holidayBoundary.setManagers();
        this.movieBoundary.setBoundaries();         this.movieBoundary.setManagers();
        this.reviewBoundary.setBoundaries();        this.reviewBoundary.setManagers();
        this.screenBoundary.setBoundaries();        this.screenBoundary.setManagers();
        this.showBoundary.setBoundaries();          this.showBoundary.setManagers();
        this.ticketPriceBoundary.setBoundaries();   this.ticketPriceBoundary.setManagers();
        this.staffBoundary.setBoundaries();         this.staffBoundary.setManagers();
        this.movieGoerBoundary.setBoundaries();     this.movieGoerBoundary.setManagers();

        // call all entity managers prime method to prime the array lists from files
        this.primeAllObjectsFromDataFiles();
    }

    private void primeAllObjectsFromDataFiles() throws IOException, ParseException {
        this.holidayMgr.primeHolidays();
        this.cineplexMgr.primeCineplex();
        this.screenMgr.primeScreen();
        this.movieMgr.primeMovie();
        this.showMgr.primeShow();
        this.userMgr.primeUser();
        this.bookingMgr.primeBookings();
        this.reviewMgr.primeViewerRatings();
        this.ticketPriceMgr.primeTicketPrice();
    }

    /** 
     * Method to set master array of bookings
     * @param masterBookings New master array of all bookings
     */
    // MasterArrays
    public void setMasterBookings(ArrayList<BookingEY> masterBookings) {
        this.masterBookings = masterBookings;
    }

    
   /** 
     * Method to get master array list of bookings
     * @return master array list of bookings
     */
    public ArrayList<BookingEY> getMasterBookings() {
        return masterBookings;
    }

    
    /** 
     * Method to set master array of cineplexes
     * @param masterCineplexes New master array of all cineplexes
     */
    public void setMasterCineplexes(ArrayList<CineplexEY> masterCineplexes) {
        this.masterCineplexes = masterCineplexes;
    }

    
    /** 
     * Method to get master array list of cineplexes
     * @return master array list of cineplexes
     */
    public ArrayList<CineplexEY> getMasterCineplexes() {
        return masterCineplexes;
    }

    
    /** 
     * Method to set master array of holidays
     * @param masterHolidays New master array of all holidays
     */
    public void setMasterHolidays(ArrayList<String> masterHolidays) {
        this.masterHolidays = masterHolidays;
    }

    
    /** 
     * Method to get master array list of holidays
     * @return master array list of holidays
     */
    public ArrayList<String> getMasterHolidays() {
        return masterHolidays;
    }

    
    /** 
     * Method to set master array of movies
     * @param masterMovies New master array of all movies
     */
    public void setMasterMovies(ArrayList<MovieEY> masterMovies) {
        this.masterMovies = masterMovies;
    }

    
    /** 
     * Method to get master array list of movies
     * @return master array list of movies
     */
    public ArrayList<MovieEY> getMasterMovies() {
        return masterMovies;
    }

    
    /** 
     * Method to set master array of ratings
     * @param masterRatings New master array of all ratings
     */
    public void setMasterRatings(ArrayList<ReviewEY> masterRatings) {
        this.masterRatings = masterRatings;
    }

    
    /** 
     * Method to get master array list of ratings
     * @return master array list of ratings
     */
    public ArrayList<ReviewEY> getMasterRatings() {
        return masterRatings;
    }

    
    /** 
     * Method to set master array of screens
     * @param masterScreens New master array of all screens
     */
    public void setMasterScreens(ArrayList<ScreenEY> masterScreens) {
        this.masterScreens = masterScreens;
    }

    
    /** 
     * Method to get master array list of screens
     * @return master array list of screens
     */
    public ArrayList<ScreenEY> getMasterScreens() {
        return masterScreens;
    }

    
    /** 
     * Method to set master array of shows
     * @param masterShows New master array of all shows
     */
    public void setMasterShows(ArrayList<ShowEY> masterShows) {
        this.masterShows = masterShows;
    }

    
    /** 
     * Method to get master array list of shows
     * @return master array list of shows
     */
    public ArrayList<ShowEY> getMasterShows() {
        return masterShows;
    }

    
    /** 
     * Method to set master array of users
     * @param masterUsers New master array of all users
     */
    public void setMasterUsers(ArrayList<UserEY> masterUsers) {
        this.masterUsers = masterUsers;
    }

    
    /** 
     * Method to get master array list of users
     * @return master array list of users
     */
    public ArrayList<UserEY> getMasterUsers() {
        return masterUsers;
    }

    
    /** 
     * Method to set master array of movie goers
     * @param masterMovieGoers New master array of all movie goers
     */
    public void setMasterMovieGoers(ArrayList<MovieGoerEY> masterMovieGoers) {
        this.masterMovieGoers = masterMovieGoers;
    }

    
    /** 
     * Method to get master array list of movie goers
     * @return master array list of movie goers
     */
    public ArrayList<MovieGoerEY> getMasterMovieGoers() {
        return masterMovieGoers;
    }

    
    /** 
     * Method to set master array of ticket prices
     * @param masterTicketPrices New master array of all ticket prices
     */
    public void setMasterTicketPrices(ArrayList<TicketPrice> masterTicketPrices) {
        this.masterTicketPrices = masterTicketPrices;
    }

    
    /** 
     * Method to get master array list of ticket prices
     * @return master array list of ticket prices
     */
    public ArrayList<TicketPrice> getMasterTicketPrices() {
        return this.masterTicketPrices;
    }

    
    /** 
     * Set method for Booking Controller.Manager
     * @param bookingMgr The object of Controller.BookingManager
     */
    // Managers
    public void setBookingMgr(BookingManager bookingMgr) {
        this.bookingMgr = bookingMgr;
    }

    
    /** 
     * Method to get the current object of Controller.BookingManager
     * @return the object of Controller.BookingManager
     */
    public BookingManager getBookingMgr() {
        return bookingMgr;
    }

    
    /** 
     * Set method for Cineplex Controller.Manager
     * @param cineplexMgr The object of Controller.CineplexManager
     */
    public void setCineplexMgr(CineplexManager cineplexMgr) {
        this.cineplexMgr = cineplexMgr;
    }

    
    /** 
     * Method to get the current object of Controller.CineplexManager
     * @return the object of Controller.CineplexManager
     */
    public CineplexManager getCineplexMgr() {
        return cineplexMgr;
    }

    
   /** 
     * Set method for Holiday Controller.Manager
     * @param holidayMgr The object of Controller.HolidayManager
     */
    public void setHolidayMgr(HolidayManager holidayMgr) {
        this.holidayMgr = holidayMgr;
    }

    
    /** 
     * Method to get the current object of Controller.HolidayManager
     * @return the object of Controller.HolidayManager
     */
    public HolidayManager getHolidayMgr() {
        return holidayMgr;
    }

    
    /** 
     * Set method for Movie Controller.Manager
     * @param movieMgr The object of Controller.MovieManager
     */
    public void setMovieMgr(MovieManager movieMgr) {
        this.movieMgr = movieMgr;
    }

    
    /** 
     * Method to get the current object of Controller.MovieManager
     * @return the object of Controller.MovieManager
     */
    public MovieManager getMovieMgr() {
        return movieMgr;
    }

    
    /** 
     * Set method for Review Controller.Manager
     * @param reviewMgr The object of Controller.ReviewManager
     */
    public void setReviewMgr(ReviewManager reviewMgr) {
        this.reviewMgr = reviewMgr;
    }

    
    /** 
     * Method to get the current object of Controller.ReviewManager
     * @return the object of Controller.ReviewManager
     */
    public ReviewManager getReviewMgr() {
        return reviewMgr;
    }

    
    /** 
     * Set method for Screen Controller.Manager
     * @param screenMgr The object of Controller.ScreenManager
     */
    public void setScreenMgr(ScreenManager screenMgr) {
        this.screenMgr = screenMgr;
    }

    
    /** 
     * Method to get the current object of Controller.ScreenManager
     * @return the object of Controller.ScreenManager
     */
    public ScreenManager getScreenMgr() {
        return screenMgr;
    }

    
    /** 
     * Set method for Show Controller.Manager
     * @param showMgr The object of Controller.ShowManager
     */
    public void setShowMgr(ShowManager showMgr) {
        this.showMgr = showMgr;
    }

    
    /** 
     * Method to get the current object of Controller.ShowManager
     * @return the object of Controller.ShowManager
     */
    public ShowManager getShowMgr() {
        return showMgr;
    }

    
    /** 
     * Set method for Ticket Controller.Manager
     * @param ticketPriceMgr The object of TicketPriceManager
     */
    public void setTicketPriceMgr(TicketPriceManager ticketPriceMgr) {
        this.ticketPriceMgr = ticketPriceMgr;
    }

    
    /** 
     * Method to get the current object of Controller.TicketPriceManager
     * @return the object of Controller.TicketPriceManager
     */
    public TicketPriceManager getTicketPriceMgr() {
        return ticketPriceMgr;
    }

    
    /** 
     * Set method for Movie Goer Controller.Manager
     * @param movieGoerMgr The object of Controller.MovieGoerManager
     */
    public void setMovieGoerMgr(MovieGoerManager movieGoerMgr) {
        this.movieGoerMgr = movieGoerMgr;
    }

    
    /** 
     * Method to get the current object of Controller.MovieGoerManager
     * @return the object of Controller.MovieGoerManager
     */
    public MovieGoerManager getMovieGoerMgr() {
        return this.movieGoerMgr;
    }

    
    /** 
     * Method to get the current object of Controller.UserManager
     * @return the object of Controller.UserManager
     */
    public UserManager getUserMgr() {
        return userMgr;
    }

    
    /** 
     * Set method for User Controller.Manager
     * @param userMgr The object of Controller.UserManager
     */
    public void setUserMgr(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    
    /** 
     * Method to get the current object of Controller.IoManager
     * @return the object of Controller.IoManager
     */
    public IoManager getIoManager() {
        return ioManager;
    }

    
    /** 
     * Set method for Io Controller.Manager
     * @param ioManager The object of Controller.IoManager
     */
    public void setIoManager(IoManager ioManager) {
        this.ioManager = ioManager;
    }


    
    /** 
     * Method to set new Boundary.BookingBoundary object
     * @param bookingBoundary New Boundary.BookingBoundary Object
     */
    // Boundaries
    public void setBookingBoundary(BookingBoundary bookingBoundary) {
        this.bookingBoundary = bookingBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.BookingBoundary
     * @return the object of Boundary.BookingBoundary
     */
    public BookingBoundary getBookingBoundary() {
        return bookingBoundary;
    }

    
    /** 
     * Method to set new Boundary.CineplexBoundary object
     * @param cineplexBoundary New Boundary.CineplexBoundary Object
     */
    public void setCineplexBoundary(CineplexBoundary cineplexBoundary) {
        this.cineplexBoundary = cineplexBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.CineplexBoundary
     * @return the object of Boundary.CineplexBoundary
     */
    public CineplexBoundary getCineplexBoundary() {
        return cineplexBoundary;
    }

    
    /** 
     * Method to set new Boundary.HolidayBoundary object
     * @param holidayBoundary New Holiday.HolidayBoundary Object
     */
    public void setHolidayBoundary(HolidayBoundary holidayBoundary) {
        this.holidayBoundary = holidayBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.HolidayBoundary
     * @return the object of Boundary.HolidayBoundary
     */
    public HolidayBoundary getHolidayBoundary() {
        return holidayBoundary;
    }

    
    /** 
     * Method to set new Boundary.MovieBoundary object
     * @param movieBoundary New Boundary.MovieBoundary Object
     */
    public void setMovieBoundary(MovieBoundary movieBoundary) {
        this.movieBoundary = movieBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.MovieBoundary
     * @return the object of Boundary.MovieBoundary
     */
    public MovieBoundary getMovieBoundary() {
        return movieBoundary;
    }

    
    /** 
     * Method to set new Boundary.ReviewBoundary object
     * @param reviewBoundary New Boundary.ReviewBoundary Object
     */
    public void setReviewBoundary(ReviewBoundary reviewBoundary) {
        this.reviewBoundary = reviewBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.ReviewBoundary
     * @return the object of Boundary.ReviewBoundary
     */
    public ReviewBoundary getReviewBoundary() {
        return reviewBoundary;
    }

    
    /** 
     * Method to set new Boundary.ScreenBoundary object
     * @param screenBoundary New Boundary.ScreenBoundary Object
     */
    public void setScreenBoundary(ScreenBoundary screenBoundary) {
        this.screenBoundary = screenBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.ScreenBoundary
     * @return the object of Boundary.ScreenBoundary
     */
    public ScreenBoundary getScreenBoundary() {
        return screenBoundary;
    }

    
    /** 
     * Method to set new Boundary.TicketPriceBoundary object
     * @param ticketPriceBoundary New Boundary.TicketPriceBoundary Object
     */
    public void setTicketPriceBoundary(TicketPriceBoundary ticketPriceBoundary) {
        this.ticketPriceBoundary = ticketPriceBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.TicketPriceBoundary
     * @return the object of Boundary.TicketPriceBoundary
     */
    public TicketPriceBoundary getTicketPriceBoundary() {
        return ticketPriceBoundary;
    }

    
    /** 
     * Method to set new Boundary.ShowBoundary object
     * @param showBoundary New Boundary.ShowBoundary Object
     */
    public void setShowBoundary(ShowBoundary showBoundary) {
        this.showBoundary = showBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.ShowBoundary
     * @return the object of Boundary.ShowBoundary
     */
    public ShowBoundary getShowBoundary() {
        return showBoundary;
    }

    
    /** 
     * Method to get the object of Boundary.StaffBoundary
     * @return the object of Boundary.StaffBoundary
     */
    public StaffBoundary getStaffBoundary() {
        return staffBoundary;
    }

    
    /** 
     * Method to set new Boundary.StaffBoundary object
     * @param staffBoundary New Boundary.StaffBoundary Object
     */
    public void setStaffBoundary(StaffBoundary staffBoundary) {
        this.staffBoundary = staffBoundary;
    }


    /**
     * Method to set get MovieGoerBoundary object
     * @return The movieGoer boundary object
     */
    public MovieGoerBoundary getMovieGoerBoundary() {
        return movieGoerBoundary;
    }

    
    /** Method to get new data folder
     * @return the data folder
     */

    public String getDataFolder() {
        return dataFolder;
    }

    
    /** 
     * Method to set new location of data
     * @param dataFolder New Data Folder
     */
    public void setDataFolder(String dataFolder) {
        this.dataFolder = dataFolder;
    }

}
