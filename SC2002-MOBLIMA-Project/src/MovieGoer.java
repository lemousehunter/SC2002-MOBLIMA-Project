import java.util.*;

public class MovieGoer {
    public final String name;
    public final String userID;
    public final String email;
    public final Integer mobileNumber;
    public final Integer age;
    public BookingManager bookings;


    public MovieGoer(String name, String userID, String email, Integer mobileNumber, Integer age, ArrayList<String> bookings)
    {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.bookings = new BookingManager(this.userID);
    }

    Review reviews = new Review();


    String BookTicket(String movieName, String date, String time, String Location, String Hall){
        return this.bookings.BookTicket(
            movieName=movieName, date=date, time=time, Location=Location, Hall=Hall
        ); // returns BookingID: String
    }

    String AddReview(){
        return reviews.addReview(this.userID); // returns reviewID: String
    }

    ArrayList<String> getAllBookings(){
        return this.bookings.getAllBookings(this.userID);
    }
}

