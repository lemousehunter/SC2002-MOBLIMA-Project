import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class BookingManager {
    final private String userID;
    final private Integer count;

    private ArrayList<ArrayList<Booking>> bookingList;
    public BookingManager(String userID){
        this.userID = userID;
        this.count = 0; // to ensure that all booking IDs are unique
        this.bookingList = new ArrayList<>();
    }
    String genBookingID(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        return this.userID + currentDateTime + this.count.toString(); // bookingID
    }

    String BookTicket(String movieID, String date, String time, String Location, String Hall) {
        String BookingID = "";

        return BookingID;
    }

    public ArrayList<String> getAllBookings(String userID) {
        return ;
    }
}
