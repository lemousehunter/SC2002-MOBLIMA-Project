import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class BookingManager {
    private String userID;
    final private Integer count;

    private MovieManager movieMgr;

    private HashMap<String, ArrayList<String>> bookingUserDict; // {User: [BookingID1, BookingID2]}
    private Hashtable<String, Booking> bookingIDDict; // {bookingID: Booking}
    private ArrayList<User> masterUsers;
    private ArrayList<Movie> masterMovies;
    private ArrayList<Screen> mastersScreens;
    private ArrayList<Holidays> masterHolidays;
    public void setMasterUsers(ArrayList<User> masterUsers) {
        this.masterUsers = masterUsers;
    }
    public void setMasterMovies(ArrayList<Movie> masterMovies) {
        this.masterMovies = masterMovies;
    }
    public void setMastersScreens(ArrayList<Screen> mastersScreens) {
        this.mastersScreens = mastersScreens;
    }
    public void setMasterHolidays(ArrayList<Holidays> masterHolidays) {
        this.masterHolidays = masterHolidays;
    }
    public BookingManager(String userID){
        this.userID = userID;
        this.count = 0; // to ensure that all booking IDs are unique
        this.bookingUserDict = new HashMap<String, ArrayList<String>>();
        this.bookingIDDict = new Hashtable<String, Booking>();
        this.movieMgr = new MovieManager();
    }
    String genBookingID(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        return this.userID + currentDateTime + this.count.toString(); // bookingID
    }

    String BookTicket(String movieID, String date, String time, String Cineplex, String Hall) {
        String BookingID = genBookingID();
        Double price = ComputePrice(movieID, date, Hall);
        Booking booking = new Booking(BookingID, this.userID, movieID, Hall, Cineplex, date, time, price);
        ArrayList<String> bookingList = this.bookingUserDict.get(this.userID);
        if (bookingList == null){ // initializes user list if not exist in hashmap
            bookingList = new ArrayList<String>();
        }
        bookingList.add(BookingID); // add bookingID to user list of bookings in hashmap

        this.bookingIDDict.put(BookingID, booking); // map booking to BookingID

        return BookingID;
    }

    Double ComputePrice(String movieID, String date, String HallID){
        String movieGoerAge = null;
        String screenClass = null;
        String dayType = DayType.WEEKDAY.toString();
        String movieType = null;
        for(User user: masterUsers){
            if(user.getUserID().equals(this.userID)){
                movieGoerAge=((MovieGoer)user).getAgeType();
            }

        }
        return new TicketPriceDefault(movieID, this.userID, HallID, date).getPrice();
    }

    public ArrayList<String> getAllBookings(String userID) {
        if (Objects.equals(userID, "")){ // userID not provided so get all bookingIDs
            Collection<ArrayList<String>> bookingIDSet = this.bookingUserDict.values();
            ArrayList<ArrayList<String>> bookingIDList = new ArrayList<>(bookingIDSet);
            ArrayList<String> bookingIDConcatList = new ArrayList<String>();
            for (ArrayList<String> strings : bookingIDList) {
                bookingIDConcatList.addAll(strings);
            }
            return bookingIDConcatList;

        }
        else{ // userID provided so get all bookingIDs for that userID
            return this.bookingUserDict.get(userID);
        }
    }
}
