import java.util.ArrayList;

public class MovieGoerEY extends User{
    public final String email;
    public final Integer mobileNumber;
    public final Integer age;
    public final ArrayList<String> bookings;


    private MovieGoerAgeEN ageType;

    public MovieGoerEY(String userID, String name, String email, Integer mobileNumber, Integer age, ArrayList<String> bookings)
    {
        super(UserType.MOVIEGOER, name);
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.setUserID(userID);
        if(age<=5){
            this.ageType = MovieGoerAgeEN.CHILD;
        }
        else if(age>5 && age<=21){
            this.ageType = MovieGoerAgeEN.STUDENT;
        }
        else if(age>21 && age<=59)
            this.ageType = MovieGoerAgeEN.ADULT;
        else
            this.ageType = MovieGoerAgeEN.SENIOR;
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

