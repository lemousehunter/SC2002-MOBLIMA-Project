import java.util.*;

public class MovieGoer {
    public final String name;
    public final String userID;
    public final String email;
    public final Integer mobileNumber;
    public final Integer age;
    public BookingManager bookingMgr;

    private final ViewerRatings reviewMgr;


    public MovieGoer(String name, String userID, String email, Integer mobileNumber, Integer age, ArrayList<String> bookings)
    {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.bookingMgr = new BookingManager(this.userID);
        this.reviewMgr = new ViewerRatings(this.userID, RatingScale.valueOf(ratingScale));
    }

    public Boolean getAgeType() {
        return this.age <= 18 || this.age >= 60;
    }


    String BookTicket(String movieName, String date, String time, String Location, String Hall){
        return this.bookingMgr.BookTicket(
            movieName=movieName, date=date, time=time, Location=Location, Hall=Hall
        ); // returns BookingID: String
    }

    String AddReview(){
        return this.reviewMgr.addReview(this.userID); // returns reviewID: String
    }

    ArrayList<String> getAllBookings(){
        return this.bookingMgr.getAllBookings(this.userID);
    }
}

