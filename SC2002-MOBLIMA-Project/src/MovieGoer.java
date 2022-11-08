import java.util.*;

public class MovieGoer extends User{
    public final String email;
    public final Integer mobileNumber;
    public final Integer age;
    private MovieGoerAge ageType;
    public BookingManager bookingMgr;

    private final reviewManager reviewMgr;


    public MovieGoer(String name, String userID, String email, Integer mobileNumber, Integer age, ArrayList<String> bookings)
    {
        super(UserType.MOVIEGOER, name);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        if(age<=5){
            this.ageType = MovieGoerAge.CHILD;
        }
        else if(age>5 && age<=21){
            this.ageType = MovieGoerAge.STUDENT;
        }
        else if(age>21 && age<=59)
            this.ageType = MovieGoerAge.ADULT;
        else
            this.ageType = MovieGoerAge.SENIOR;
        this.bookingMgr = new BookingManager(super.getUserID());
        this.reviewMgr = new reviewManager();
    }

    public String getAgeType() {
        return this.ageType.toString();
    }


    String BookTicket(String movieName, String date, String time, String Location, String Hall){
        return this.bookingMgr.BookTicket(
            movieName=movieName, date=date, time=time, Location=Location, Hall=Hall
        ); // returns BookingID: String
    }

    String addReview(){
        return this.reviewMgr.addReview();
    }

    ArrayList<String> getAllBookings(){
        return this.bookingMgr.getAllBookings(super.getUserID());
    }
}

