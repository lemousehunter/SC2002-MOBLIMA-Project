import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager implements Manager {

  private final Integer count;

  private final MovieManager movieMgr;

  private final HashMap<String, ArrayList<String>> bookingUserDict; // {User: [BookingID1, BookingID2]}
  private final Hashtable<String, Booking> bookingIDDict; // {bookingID: Booking}
  private Ticket ticket;
  private final HolidayManager holidayManager;
  private ArrayList<User> masterUserList;
  private ArrayList<Cineplex> masterCineplexes;
  private ArrayList<Screen> masterScreens;
  private ArrayList<Booking> masterBookings;
  private ArrayList<Show> masterShows;
  private ArrayList<Movie> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ViewerRatings> masterRatings;
  private ArrayList<ShowSeat> masterShowSeats;
  private SeatManager seatMgr;


  @Override
  public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<Cineplex> masterCineplexes,
    ArrayList<Screen> masterScreens,
    ArrayList<Booking> masterBookings,
    ArrayList<Show> masterShows,
    ArrayList<Movie> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ViewerRatings> masterRatings,
    ArrayList<ShowSeat> masterShowSeats
  ) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
    this.masterShowSeats = masterShowSeats;
  }

  public BookingManager(MovieManager movieMgr, HolidayManager holidayManager, SeatManager seatMgr) {
    this.count = 0; // to ensure that all booking IDs are unique
    this.bookingUserDict = new HashMap<String, ArrayList<String>>();
    this.bookingIDDict = new Hashtable<String, Booking>();
    this.movieMgr = movieMgr;
    this.holidayManager = holidayManager;
    this.seatMgr = seatMgr;
    this.initializeHashMaps();
  }

  public Booking getBookingByID(String bookingID) {
    return this.bookingIDDict.get(bookingID);
  }

  private void initializeHashMaps() {
    if (this.masterUserList != null) {
      for (User user : this.masterUserList) {
        this.bookingUserDict.put(user.getUserID(), new ArrayList<String>());
      }
    }
    ArrayList<String> user_booking_lst;
    if (this.masterBookings != null) {
      for (Booking booking : this.masterBookings) {
        user_booking_lst = this.bookingUserDict.get(booking.getUserID());
        user_booking_lst.add(booking.getBookingID());
        this.bookingIDDict.put(booking.getBookingID(), booking);
      }
    }
  }

  private String genBookingID(String userID) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    LocalDateTime now = LocalDateTime.now();
    String currentDateTime = dtf.format(now);
    return userID + currentDateTime + this.count.toString(); // bookingID
  }

  public String BookTicket( String userID, String movieID, String date, String time, String cineplexID, String screenID, ArrayList<String> seatIDs) throws ParseException {
    String BookingID = genBookingID(userID);
    Booking booking = new Booking(BookingID, userID, movieID, screenID, cineplexID, date, time, seatIDs, -1, this.holidayManager, this.movieMgr);
    ArrayList<String> bookingList = this.bookingUserDict.get(userID);
    if (bookingList == null) { // initializes user list if not exist in hashmap
      bookingList = new ArrayList<String>();
    }
    bookingList.add(BookingID); // add bookingID to user list of bookings in hashmap

    this.bookingIDDict.put(BookingID, booking); // map booking to BookingID

    // Add BookingID to corresponding user

    for (User user : masterUserList){
        if (user.getUserID().equals(userID)){
            MovieGoer movieGoer = (MovieGoer) user;
            movieGoer.addBookingID(BookingID);
        }
    }

    // Add Bookings to Masterlist
    masterBookings.add(booking);

    return BookingID;
  }

  public ArrayList<String> getAllBookings(String userID) {
    if (Objects.equals(userID, "")) { // userID not provided so get all bookingIDs
      Collection<ArrayList<String>> bookingIDSet =
        this.bookingUserDict.values();
      ArrayList<ArrayList<String>> bookingIDList = new ArrayList<>(
        bookingIDSet
      );
      ArrayList<String> bookingIDConcatList = new ArrayList<String>();
      for (ArrayList<String> strings : bookingIDList) {
        bookingIDConcatList.addAll(strings);
      }
      return bookingIDConcatList;
    } else { // userID provided so get all bookingIDs for that userID
      return this.bookingUserDict.get(userID);
    }
  }

  public ArrayList<String> getTop5Movies() {
    Hashtable<String, Double> costDict = new Hashtable<String, Double>(); // costDict[MovieID]
    masterMovies.forEach((movie) -> costDict.put(movie.getMovieID(), 0.0)); // initialize costDict with all movieIDs,0.0 in masterMovies
    for (String bookingID : bookingIDDict.keySet()) { // for all bookings
      Booking booking = this.getBookingByID(bookingID);
      Double cost = booking.getBookingAmount(); // get cost of booking
      cost += costDict.get(booking.getMovieID()); // update cost var with running total
      costDict.put(booking.getMovieID(), cost); // write cost back to costDict
    }

    Hashtable<String, Double> top5Dict = new Hashtable<String, Double>();
    double minCost;
    String minKey;
    for (String movieID: costDict.keySet()) {
      double currentCost = costDict.get(movieID); // gets current cost of movie from costDict
      if (top5Dict.size() < 5) {
        top5Dict.put(movieID, currentCost);
      }
      else {
        minCost = 0;
        minKey = "";
        for (String _movieID: top5Dict.keySet()) { // get minCost and Key
          if (top5Dict.get(_movieID) <= minCost) {
            minCost = top5Dict.get(_movieID);
            minKey = _movieID;
          }
        }
        if (currentCost >= minCost) {
          top5Dict.remove(minKey);
          top5Dict.put(movieID, currentCost);
        }
      }
    }
    ArrayList<String> top5List = new ArrayList<String>();
    int idx = 0;
    for (String mID: top5Dict.keySet()) {
      top5List.add(idx, mID);
      idx++;
    }
    return top5List;
  }

  public ArrayList<String> getListOfSeats (String bookingID){
    ArrayList<Ticket> tickets = getBookingByID(bookingID).getTickets();
    ArrayList<String> seats = new ArrayList<String>();
    for (Ticket ticket: tickets) {
      String seatID = ticket.getSeatId();
      Seat seat = this.seatMgr.getSeatByID(seatID);
      String seatNumber = String.valueOf(seat.getSeatNumber());
      String rowNumber = seat.getSeatRow();
      seats.add("R" + rowNumber + "S" + seatNumber);
    }

    return seats;
  }
}
