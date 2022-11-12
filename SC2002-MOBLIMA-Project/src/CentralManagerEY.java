import java.util.ArrayList;

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
    private StaffBoundary staffBoundary;

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
 
    }

    // MasterArrays
    public void setMasterBookings(ArrayList<BookingEY> masterBookings) {
        this.masterBookings = masterBookings;
    }

    public ArrayList<BookingEY> getMasterBookings() {
        return masterBookings;
    }

    public void setMasterCineplexes(ArrayList<CineplexEY> masterCineplexes) {
        this.masterCineplexes = masterCineplexes;
    }

    public ArrayList<CineplexEY> getMasterCineplexes() {
        return masterCineplexes;
    }

    public void setMasterHolidays(ArrayList<String> masterHolidays) {
        this.masterHolidays = masterHolidays;
    }

    public ArrayList<String> getMasterHolidays() {
        return masterHolidays;
    }

    public void setMasterMovies(ArrayList<MovieEY> masterMovies) {
        this.masterMovies = masterMovies;
    }

    public ArrayList<MovieEY> getMasterMovies() {
        return masterMovies;
    }

    public void setMasterRatings(ArrayList<ReviewEY> masterRatings) {
        this.masterRatings = masterRatings;
    }

    public ArrayList<ReviewEY> getMasterRatings() {
        return masterRatings;
    }

    public void setMasterScreens(ArrayList<ScreenEY> masterScreens) {
        this.masterScreens = masterScreens;
    }

    public ArrayList<ScreenEY> getMasterScreens() {
        return masterScreens;
    }

    public void setMasterShows(ArrayList<ShowEY> masterShows) {
        this.masterShows = masterShows;
    }

    public ArrayList<ShowEY> getMasterShows() {
        return masterShows;
    }

    public void setMasterUsers(ArrayList<User> masterUsers) {
        this.masterUsers = masterUsers;
    }

    public ArrayList<User> getMasterUsers() {
        return masterUsers;
    }

    public void setMasterMovieGoers(ArrayList<MovieGoerEY> masterMovieGoers) {
        this.masterMovieGoers = masterMovieGoers;
    }

    public ArrayList<MovieGoerEY> getMasterMovieGoers() {
        return masterMovieGoers;
    }

    public void setMasterTicketPrices(ArrayList<TicketPrice> masterTicketPrices) {
        this.masterTicketPrices = masterTicketPrices;
    }

    public ArrayList<TicketPrice> getMasterTicketPrices() {
        return this.masterTicketPrices;
    }

    // Managers
    public void setBookingMgr(BookingManager bookingMgr) {
        this.bookingMgr = bookingMgr;
    }

    public BookingManager getBookingMgr() {
        return bookingMgr;
    }

    public void setCineplexMgr(CineplexManager cineplexMgr) {
        this.cineplexMgr = cineplexMgr;
    }

    public CineplexManager getCineplexMgr() {
        return cineplexMgr;
    }

    public void setHolidayMgr(HolidayManager holidayMgr) {
        this.holidayMgr = holidayMgr;
    }

    public HolidayManager getHolidayMgr() {
        return holidayMgr;
    }

    public void setMovieMgr(MovieManager movieMgr) {
        this.movieMgr = movieMgr;
    }

    public MovieManager getMovieMgr() {
        return movieMgr;
    }

    public void setReviewMgr(ReviewManager reviewMgr) {
        this.reviewMgr = reviewMgr;
    }

    public ReviewManager getReviewMgr() {
        return reviewMgr;
    }

    public void setScreenMgr(ScreenManager screenMgr) {
        this.screenMgr = screenMgr;
    }

    public ScreenManager getScreenMgr() {
        return screenMgr;
    }

    public void setShowMgr(ShowManager showMgr) {
        this.showMgr = showMgr;
    }

    public ShowManager getShowMgr() {
        return showMgr;
    }

    public void setTicketPriceMgr(TicketPriceManager ticketPriceMgr) {
        this.ticketPriceMgr = ticketPriceMgr;
    }

    public TicketPriceManager getTicketPriceMgr() {
        return ticketPriceMgr;
    }

    public void setMovieGoerMgr(MovieGoerManager movieGoerMgr) {
        this.movieGoerMgr = movieGoerMgr;
    }

    public MovieGoerManager getMovieGoerMgr() {
        return this.movieGoerMgr;
    }

    public UserManager getUserMgr() {
        return userMgr;
    }

    public void setUserMgr(UserManager userMgr) {
        this.userMgr = userMgr;
    }

    public IoManager getIoManager() {
        return ioManager;
    }

    public void setIoManager(IoManager ioManager) {
        this.ioManager = ioManager;
    }


    // Boundaries
    public void setBookingBoundary(BookingBoundary bookingBoundary) {
        this.bookingBoundary = bookingBoundary;
    }

    public BookingBoundary getBookingBoundary() {
        return bookingBoundary;
    }

    public void setCineplexBoundary(CineplexBoundary cineplexBoundary) {
        this.cineplexBoundary = cineplexBoundary;
    }

    public CineplexBoundary getCineplexBoundary() {
        return cineplexBoundary;
    }

    public void setHolidayBoundary(HolidayBoundary holidayBoundary) {
        this.holidayBoundary = holidayBoundary;
    }

    public HolidayBoundary getHolidayBoundary() {
        return holidayBoundary;
    }

    public void setMovieBoundary(MovieBoundary movieBoundary) {
        this.movieBoundary = movieBoundary;
    }

    public MovieBoundary getMovieBoundary() {
        return movieBoundary;
    }

    public void setReviewBoundary(ReviewBoundary reviewBoundary) {
        this.reviewBoundary = reviewBoundary;
    }

    public ReviewBoundary getReviewBoundary() {
        return reviewBoundary;
    }

    public void setScreenBoundary(ScreenBoundary screenBoundary) {
        this.screenBoundary = screenBoundary;
    }

    public ScreenBoundary getScreenBoundary() {
        return screenBoundary;
    }

    public void setTicketPriceBoundary(TicketPriceBoundary ticketPriceBoundary) {
        this.ticketPriceBoundary = ticketPriceBoundary;
    }

    public TicketPriceBoundary getTicketPriceBoundary() {
        return ticketPriceBoundary;
    }

    public void setShowBoundary(ShowBoundary showBoundary) {
        this.showBoundary = showBoundary;
    }

    public ShowBoundary getShowBoundary() {
        return showBoundary;
    }

    public StaffBoundary getStaffBoundary() {
        return staffBoundary;
    }

    public void setStaffBoundary(StaffBoundary staffBoundary) {
        this.staffBoundary = staffBoundary;
    }
    public String getDataFolder() {
        return dataFolder;
    }

    public void setDataFolder(String dataFolder) {
        this.dataFolder = dataFolder;
    }

}
