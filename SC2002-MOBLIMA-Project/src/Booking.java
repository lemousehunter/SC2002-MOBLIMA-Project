import java.awt.print.Book;
import java.util.ArrayList;

public class Booking {
    public String bookingID;
    public String userID;
    public String movieID;
    public String hallID;
    public String cinemaID;
    public String date;
    public String time;
    public Double price;

    public Booking(String bookingID, String userID, String movieID, String hallID, String cinemaID, String date, String time, Double price){
        this.bookingID = bookingID;
        this.userID = userID;
        this.movieID = movieID;
        this.hallID = hallID;
        this.cinemaID = cinemaID;
        this.date = date;
        this.time = time;
        this.price = price;
    }
}
