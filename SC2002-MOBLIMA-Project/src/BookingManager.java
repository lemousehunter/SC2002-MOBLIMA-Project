import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BookingManager {
    final private String userID;
    final private Integer count;

    private MovieManager movieMgr;

    private HashMap<String, ArrayList<String>> bookingUserDict; // {User: [BookingID1, BookingID2]}
    private Hashtable<String, Booking> bookingIDDict; // {bookingID: Booking}
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

    Double ComputePrice(String movieID, String date, String Hall){
        Movie movie = new Movie();
        Boolean isHoliday = Holidays.getHoliday(); // 1: holiday, 0: not holiday
        Boolean isWeekend = Holidays.getWeekend(); // 1: weekend, 0: weekday
        boolean isSpecial = isHoliday || isWeekend; // 1: special, 0: weekday

        movie = this.movieMgr.getMovieByID(movieID);
        Boolean HallType = Hall.type; // 1: Premium Hall, 0: Regular Hall

        MovieGoerManager movieGoerMgr = new MovieGoerManager();
        MovieGoer movieGoer = movieGoerMgr.getUserByID(this.userID);

        Boolean movieGoerAge = movieGoer.getAgeType(); // 1: discounted price, 0: normal price

        double price;

        if (movie.getMovieType()){ // 1: blockbuster, 0: regular movies
            price = 15.0;
        }
        else{
            price = 10.0;
        }

        if (isSpecial){
            price *= 1.5;
        }

        if (HallType){
            price *= 2;
        }

        if (HallType){
            price *= 1;
        }

        if (movieGoerAge){
            price *= 0.5;
        }

        return price;
    }

    public ArrayList<String> getAllBookings(String userID) {
        if (Objects.equals(userID, "")){ // userID not provided so get all bookingIDs
            Set<String> bookingIDSet = this.bookingIDDict.keySet();
            return new ArrayList<>(bookingIDSet);
        }
        else{ // userID provided so get all bookingIDs for that userID
            return this.bookingUserDict.get(userID);
        }
    }
}
