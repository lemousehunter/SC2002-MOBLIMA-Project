import java.util.ArrayList;

public class MovieGoer extends User{
    public final String email;
    public final Integer mobileNumber;
    public final Integer age;
    public final ArrayList<String> bookings;


    private MovieGoerAge ageType;

    public MovieGoer(String userID, String name, String email, Integer mobileNumber, Integer age, ArrayList<String> bookings)
    {
        super(UserType.MOVIEGOER, name);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.setUserID(userID);
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
        this.bookings=bookings;

    }

    public String getAgeType() {
        return this.ageType.toString();
    }
    public String getEmailID() { return this.email; }

    public Integer getMobileNumber() { return this.mobileNumber; }

    public Integer getAge() { return this.age; }

    public ArrayList<String> getBookings() { return this.bookings; }

    public void addBookingID (String bookingID) {
        bookings.add(bookingID);
    }

}

