import java.util.ArrayList;

public class CentralManagerEY {
    // Master Lists
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewEY> masterRatings;

    // Managers
    private BookingManager bookingMgr;
    private CineplexManager cineplexMgr;
    private HolidayManager holidayMgr;
    private MovieManager movieMgr;
    private ReviewManager reviewMgr;
    private ScreenManager screenMgr;
    private SeatManager seatMgr;
    private ShowManager showMgr;
    private TicketPriceManager ticketPriceMgr;

    public CentralManagerEY() {

    }

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

    public void setMasterHolidaysList(ArrayList<String> masterHolidaysList) {
        this.masterHolidaysList = masterHolidaysList;
    }

    public ArrayList<String> getMasterHolidaysList() {
        return masterHolidaysList;
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

    public void setMasterUserList(ArrayList<User> masterUserList) {
        this.masterUserList = masterUserList;
    }

    public ArrayList<User> getMasterUserList() {
        return masterUserList;
    }

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

    public void setSeatMgr(SeatManager seatMgr) {
        this.seatMgr = seatMgr;
    }

    public SeatManager getSeatMgr() {
        return seatMgr;
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
}
