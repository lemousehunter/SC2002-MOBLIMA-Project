import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingManager extends Manager implements BaseManager {

  private Integer count;

  private HashMap<String, ArrayList<String>> bookingUserDict; // {User: [BookingID1, BookingID2]}
  private Hashtable<String, BookingEY> bookingIDDict; // {bookingID: Booking}
  private TicketEY ticket;

  // Managers
  private HolidayManager holidayMgr;
  private MovieManager movieMgr;
  private MovieGoerManager movieGoerMgr;
  private ScreenManager screenManager;
  private IoManager ioManager;
  private TicketPriceManager ticketPriceManager;

  // MasterLists
  private ArrayList<MovieGoerEY> masterMovieGoers;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<MovieEY> masterMovies;


  public BookingManager() {
    this.count = 0; // to ensure that all booking IDs are unique
    this.bookingUserDict = new HashMap<String, ArrayList<String>>();
    this.bookingIDDict = new Hashtable<String, BookingEY>();
  }

  @Override
  public void setManagers() {
    this.holidayMgr = this.getCentralManager().getHolidayMgr();
    this.movieMgr = this.getCentralManager().getMovieMgr();
    this.movieGoerMgr = this.getCentralManager().getMovieGoerMgr();
    this.ioManager = this.getCentralManager().getIoManager();
    this.screenManager = this.getCentralManager().getScreenMgr();
    this.ticketPriceManager = this.getCentralManager().getTicketPriceMgr();
    this.initializeHashMaps();
  }

  @Override
  public void setMasterLists() {
    CentralManagerEY centralMgr = this.getCentralManager();
    this.masterBookings = centralMgr.getMasterBookings();
    this.masterMovies = centralMgr.getMasterMovies();
    this.masterMovieGoers = centralMgr.getMasterMovieGoers();
  }

  public BookingEY getBookingByID(String bookingID) {
    return this.bookingIDDict.get(bookingID);
  }

  private void initializeHashMaps() {
    ArrayList<MovieGoerEY> movieGoerList = this.movieGoerMgr.getAllMovieGoers();
    if (movieGoerList != null) {
      for (MovieGoerEY movieGoer : movieGoerList) {
        this.bookingUserDict.put(movieGoer.getUserID(), new ArrayList<String>());
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
    BookingEY booking = new BookingEY(BookingID, userID, movieID, screenID, cineplexID, date, time, seatIDs, -1, this.holidayMgr, this.movieMgr, this.screenManager, this.movieGoerMgr, this. ticketPriceManager);
    ArrayList<String> bookingList = this.bookingUserDict.get(userID);
    if (bookingList == null) { // initializes user list if not exist in hashmap
      bookingList = new ArrayList<String>();
    }
    bookingList.add(BookingID); // add bookingID to user list of bookings in hashmap

    this.bookingIDDict.put(BookingID, booking); // map booking to BookingID

    // Add BookingID to corresponding user
    this.movieGoerMgr.getUserByID(userID).addBookingID(BookingID);

    // Add Bookings to Masterlist
    this.masterBookings.add(booking);

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
    this.masterMovies.forEach((movie) -> costDict.put(movie.getMovieID(), 0.0)); // initialize costDict with all movieIDs,0.0 in masterMovies
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

  public void primeBookings() throws IOException, ParseException {
    String bookingSEPARATOR = "|";
    String SeatSEPARATOR = "~";
    ArrayList stringArray = null;
    String filename = this.getCentralManager().getDataFolder().concat("Bookings.txt");
    try {
        stringArray = (ArrayList) ioManager.read(filename);
    } catch (FileNotFoundException e) {
        System.out.println("Priming of Booking objects is skipped as there is no master data");
        return;
    }

    for (int i = 0; i < stringArray.size(); i++) {
        String st = (String) stringArray.get(i);
        // get individual 'fields' of the string separated by SEPARATOR
        StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string
                                                                         // tokenizer using delimiter ","
        String bookingID = star.nextToken().trim(); // first token
        String userID = star.nextToken().trim();
        String movieID = star.nextToken().trim();
        String screenID = star.nextToken().trim();
        String cineplexID = star.nextToken().trim();
        String date = star.nextToken().trim();
        String time = star.nextToken().trim();
        Double price = Double.valueOf(star.nextToken().trim());
        ArrayList<String> seatIds = new ArrayList<String>();
        String seatID = null;
        String seatIdString = star.nextToken().trim();
        StringTokenizer SeatsToken = new StringTokenizer(seatIdString, SeatSEPARATOR);
        while (SeatsToken.hasMoreTokens()) {
            seatID = SeatsToken.nextToken().trim();
            seatIds.add(seatID);
        }
        BookingEY booking = new BookingEY(bookingID, userID, movieID, screenID, cineplexID, date, time, seatIds,
                price, this.holidayMgr, this.movieMgr, this.screenManager, this.movieGoerMgr,this.ticketPriceManager);
        this.masterBookings.add(booking);
    }

}

  public void writeBookings() throws IOException {
    String bookingSEPARATOR = " | ";
    String seatSEPARATOR = " ~ ";
    String filename = this.getCentralManager().getDataFolder().concat("Bookings.txt");
    List alw = new ArrayList();
    BookingEY booking;
    for (int i = 0; i < this.masterBookings.size(); i++) {
        booking = this.masterBookings.get(i);
        StringBuilder st = new StringBuilder();
        st.append(booking.getBookingID().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getUserID().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getMovieID().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getScreenID().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getCinemaID().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getDate().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getTime().trim());
        st.append(bookingSEPARATOR);
        st.append(booking.getBookingAmount());
        st.append(bookingSEPARATOR);
        for (TicketEY ticket : booking.getTickets()) {
            st.append(ticket.getSeatId());
            st.append(seatSEPARATOR);
        }
        st.append(bookingSEPARATOR);
        alw.add(st.toString());

    }
    ioManager.write(filename, alw);

}


}
