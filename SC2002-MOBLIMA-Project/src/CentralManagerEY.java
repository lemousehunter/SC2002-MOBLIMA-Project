import java.util.ArrayList;

/**
 * A CentralManagerEY Object
 * 
 * <p>
 * A <code>CentralManagerEY</code> object contains all the master lists, and instances of all boundaries and controllers
 * </p>
*/
public class CentralManagerEY {
    // Master Lists
    private ArrayList<User> masterUsers;
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
     * Method to create an object of CentralManagerEY
     */
    public CentralManagerEY() {

        // Create Master lists

        this.masterUsers = new ArrayList<User>();
        this.masterCineplexes = new ArrayList<CineplexEY>();
        this.masterScreens = new ArrayList<ScreenEY>();
        this.masterBookings = new ArrayList<BookingEY>();
        this.masterShows = new ArrayList<ShowEY>();
        this.masterMovies = new ArrayList<MovieEY>();
        this.masterHolidays = new ArrayList<String>();
        this.masterRatings = new ArrayList<ReviewEY>();
        this.masterTicketPrices = new ArrayList<TicketPrice>();

        // Master data folder derived from datafolder property
        this.dataFolder= System.getProperty("dataFolder");
        if (this.dataFolder== null) {
            this.dataFolder = "";
            // remove below line as the folder is harcoded for testing
            this.dataFolder = "C:\\Users\\swami\\Downloads\\Varsha\\SC2002-MOBLIMA-Project\\database\\";
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
    public void setMasterUsers(ArrayList<User> masterUsers) {
        this.masterUsers = masterUsers;
    }

    
    /** 
     * Method to get master array list of users
     * @return master array list of users
     */
    public ArrayList<User> getMasterUsers() {
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
     * Set method for Booking Manager
     * @param bookingMgr The object of BookingManager
     */
    // Managers
    public void setBookingMgr(BookingManager bookingMgr) {
        this.bookingMgr = bookingMgr;
    }

    
    /** 
     * Method to get the current object of BookingManager
     * @return the object of BookingManager
     */
    public BookingManager getBookingMgr() {
        return bookingMgr;
    }

    
    /** 
     * Set method for Cineplex Manager
     * @param cineplexMgr The object of CineplexManager
     */
    public void setCineplexMgr(CineplexManager cineplexMgr) {
        this.cineplexMgr = cineplexMgr;
    }

    
    /** 
     * Method to get the current object of CineplexManager
     * @return the object of CineplexManager
     */
    public CineplexManager getCineplexMgr() {
        return cineplexMgr;
    }

    
   /** 
     * Set method for Holiday Manager
     * @param holidayMgr The object of HolidayManager
     */
    public void setHolidayMgr(HolidayManager holidayMgr) {
        this.holidayMgr = holidayMgr;
    }

    
    /** 
     * Method to get the current object of HolidayManager
     * @return the object of HolidayManager
     */
    public HolidayManager getHolidayMgr() {
        return holidayMgr;
    }

    
    /** 
     * Set method for Movie Manager
     * @param movieMgr The object of MovieManager
     */
    public void setMovieMgr(MovieManager movieMgr) {
        this.movieMgr = movieMgr;
    }

    
    /** 
     * Method to get the current object of MovieManager
     * @return the object of MovieManager
     */
    public MovieManager getMovieMgr() {
        return movieMgr;
    }

    
    /** 
     * Set method for Review Manager
     * @param reviewMgr The object of ReviewManager
     */
    public void setReviewMgr(ReviewManager reviewMgr) {
        this.reviewMgr = reviewMgr;
    }

    
    /** 
     * Method to get the current object of ReviewManager
     * @return the object of ReviewManager
     */
    public ReviewManager getReviewMgr() {
        return reviewMgr;
    }

    
    /** 
     * Set method for Screen Manager
     * @param screenMgr The object of ScreenManager
     */
    public void setScreenMgr(ScreenManager screenMgr) {
        this.screenMgr = screenMgr;
    }

    
    /** 
     * Method to get the current object of ScreenManager
     * @return the object of ScreenManager
     */
    public ScreenManager getScreenMgr() {
        return screenMgr;
    }

    
    /** 
     * Set method for Show Manager
     * @param showMgr The object of ShowManager
     */
    public void setShowMgr(ShowManager showMgr) {
        this.showMgr = showMgr;
    }

    
    /** 
     * Method to get the current object of ShowManager
     * @return the object of ShowManager
     */
    public ShowManager getShowMgr() {
        return showMgr;
    }

    
    /** 
     * Set method for Ticket Manager
     * @param ticketMgr The object of TicketManager
     */
    public void setTicketPriceMgr(TicketPriceManager ticketPriceMgr) {
        this.ticketPriceMgr = ticketPriceMgr;
    }

    
    /** 
     * Method to get the current object of TicketPriceManager
     * @return the object of TicketPriceManager
     */
    public TicketPriceManager getTicketPriceMgr() {
        return ticketPriceMgr;
    }

    
    /** 
     * Set method for Movie Goer Manager
     * @param movieGoerMgr The object of MovieGoerManager
     */
    public void setMovieGoerMgr(MovieGoerManager movieGoerMgr) {
        this.movieGoerMgr = movieGoerMgr;
    }

    
    /** 
     * Method to get the current object of MovieGoerManager
     * @return the object of MovieGoerManager
     */
    public MovieGoerManager getMovieGoerMgr() {
        return this.movieGoerMgr;
    }

    
    /** 
     * Method to get the current object of UserManager
     * @return the object of UserManager
     */
    public UserManager getUserMgr() {
        return userMgr;
    }

    
    /** 
     * Set method for User Manager
     * @param userMgr The object of UserManager
     */
    public void setUserMgr(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    
    /** 
     * Method to get the current object of IoManager
     * @return the object of IoManager
     */
    public IoManager getIoManager() {
        return ioManager;
    }

    
    /** 
     * Set method for Io Manager
     * @param ioManager The object of IoManager
     */
    public void setIoManager(IoManager ioManager) {
        this.ioManager = ioManager;
    }


    
    /** 
     * Method to set new BookingBoundary object
     * @param bookingBoundary New BookingBoundary Object
     */
    // Boundaries
    public void setBookingBoundary(BookingBoundary bookingBoundary) {
        this.bookingBoundary = bookingBoundary;
    }

    
    /** 
     * Method to get the object of BookingBoundary
     * @return the object of BookingBoundary
     */
    public BookingBoundary getBookingBoundary() {
        return bookingBoundary;
    }

    
    /** 
     * Method to set new CineplexBoundary object
     * @param cineplexBoundary New CineplexBoundary Object
     */
    public void setCineplexBoundary(CineplexBoundary cineplexBoundary) {
        this.cineplexBoundary = cineplexBoundary;
    }

    
    /** 
     * Method to get the object of CineplexBoundary
     * @return the object of CineplexBoundary
     */
    public CineplexBoundary getCineplexBoundary() {
        return cineplexBoundary;
    }

    
    /** 
     * Method to set new HolidayBoundary object
     * @param bookingBoundary New BookingBoundary Object
     */
    public void setHolidayBoundary(HolidayBoundary holidayBoundary) {
        this.holidayBoundary = holidayBoundary;
    }

    
    /** 
     * Method to get the object of HolidayBoundary
     * @return the object of HolidayBoundary
     */
    public HolidayBoundary getHolidayBoundary() {
        return holidayBoundary;
    }

    
    /** 
     * Method to set new MovieBoundary object
     * @param movieBoundary New MovieBoundary Object
     */
    public void setMovieBoundary(MovieBoundary movieBoundary) {
        this.movieBoundary = movieBoundary;
    }

    
    /** 
     * Method to get the object of MovieBoundary
     * @return the object of MovieBoundary
     */
    public MovieBoundary getMovieBoundary() {
        return movieBoundary;
    }

    
    /** 
     * Method to set new ReviewBoundary object
     * @param reviewBoundary New ReviewBoundary Object
     */
    public void setReviewBoundary(ReviewBoundary reviewBoundary) {
        this.reviewBoundary = reviewBoundary;
    }

    
    /** 
     * Method to get the object of ReviewBoundary
     * @return the object of ReviewBoundary
     */
    public ReviewBoundary getReviewBoundary() {
        return reviewBoundary;
    }

    
    /** 
     * Method to set new ScreenBoundary object
     * @param screenBoundary New ScreenBoundary Object
     */
    public void setScreenBoundary(ScreenBoundary screenBoundary) {
        this.screenBoundary = screenBoundary;
    }

    
    /** 
     * Method to get the object of ScreenBoundary
     * @return the object of ScreenBoundary
     */
    public ScreenBoundary getScreenBoundary() {
        return screenBoundary;
    }

    
    /** 
     * Method to set new TicketPriceBoundary object
     * @param TicketPriceBoundary New TicketPriceBoundary Object
     */
    public void setTicketPriceBoundary(TicketPriceBoundary ticketPriceBoundary) {
        this.ticketPriceBoundary = ticketPriceBoundary;
    }

    
    /** 
     * Method to get the object of TicketPriceBoundary
     * @return the object of TicketPriceBoundary
     */
    public TicketPriceBoundary getTicketPriceBoundary() {
        return ticketPriceBoundary;
    }

    
    /** 
     * Method to set new ShowBoundary object
     * @param showBoundary New ShowBoundary Object
     */
    public void setShowBoundary(ShowBoundary showBoundary) {
        this.showBoundary = showBoundary;
    }

    
    /** 
     * Method to get the object of ShowBoundary
     * @return the object of ShowBoundary
     */
    public ShowBoundary getShowBoundary() {
        return showBoundary;
    }

    
    /** 
     * Method to get the object of StaffBoundary
     * @return the object of StaffBoundary
     */
    public StaffBoundary getStaffBoundary() {
        return staffBoundary;
    }

    
    /** 
     * Method to set new StaffBoundary object
     * @param staffBoundary New StaffBoundary Object
     */
    public void setStaffBoundary(StaffBoundary staffBoundary) {
        this.staffBoundary = staffBoundary;
    }
<<<<<<< Updated upstream

    public MovieGoerBoundary getMovieGoerBoundary() {
        return movieGoerBoundary;
    }

=======
    
    /** Method to get new data folder
     * @return the data folder
     */
>>>>>>> Stashed changes
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
