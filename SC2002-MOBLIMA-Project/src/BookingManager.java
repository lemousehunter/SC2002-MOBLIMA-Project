import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager implements BaseManager {

  private Integer count;

  private MovieManager movieMgr;

  private HashMap<String, ArrayList<String>> bookingUserDict; // {User: [BookingID1, BookingID2]}
  private Hashtable<String, BookingEY> bookingIDDict; // {bookingID: Booking}
  private TicketEY ticket;
  private HolidayManager holidayManager;
  private CentralManagerEY centralManager;
  private Hashtable<String, BaseManager> managerDict;
  private Hashtable<String, ArrayList> arrayDict;


  public BookingManager() {
    this.count = 0; // to ensure that all booking IDs are unique
    this.bookingUserDict = new HashMap<String, ArrayList<String>>();
    this.bookingIDDict = new Hashtable<String, BookingEY>();
    this.managerDict = new Hashtable<String, BaseManager>();
    this.initializeHashMaps();
  }

  @Override
  public void setCentralManager(CentralManagerEY CentralManager) {
    this.centralManager = centralManager;
    this.setManagers();
  }

  @Override
  public void setManagers() {
    this.holidayManager = this.centralManager.getHolidayMgr();
    this.movieMgr = this.centralManager.getMovieMgr();

  }

  @Override
  public BaseManager getManager(String managerName) {
    return this.managerDict.get(managerName);
  }

  @Override
  public ArrayList getMasterList(String arrayName) {
    return this.arrayDict.get(arrayName);
  }

  public BookingEY getBookingByID(String bookingID) {
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
      for (BookingEY booking : this.masterBookings) {
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
    BookingEY booking = new BookingEY(BookingID, userID, movieID, screenID, cineplexID, date, time, seatIDs, -1, this.holidayManager, this.movieMgr);
    ArrayList<String> bookingList = this.bookingUserDict.get(userID);
    if (bookingList == null) { // initializes user list if not exist in hashmap
      bookingList = new ArrayList<String>();
    }
    bookingList.add(BookingID); // add bookingID to user list of bookings in hashmap

    this.bookingIDDict.put(BookingID, booking); // map booking to BookingID

    // Add BookingID to corresponding user

    for (User user : masterUserList){
        if (user.getUserID().equals(userID)){
            MovieGoerEY movieGoer = (MovieGoerEY) user;
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
      BookingEY booking = this.getBookingByID(bookingID);
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

  public ArrayList<String> getListOfSeats (BookingEY booking){
    ArrayList<TicketEY> tickets = booking.getTickets();
    ArrayList<String> seats = new ArrayList<String>();
    for (TicketEY ticket: tickets) {
      String seatID = ticket.getSeatId();
      seats.add(ticket.getSeatId());
    }
    return seats;
  }
}
